import java.util.concurrent.{Executors, TimeUnit}

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.{ChannelInitializer, ChannelOption}
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.http.{HttpServerCodec, HttpServerExpectContinueHandler}
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.{ChannelFutureListener, ChannelHandlerContext, ChannelInboundHandlerAdapter}
import io.netty.handler.codec.http._
import io.netty.util.AsciiString

class HttpServer() {
  def run(boss: NioEventLoopGroup, worker: NioEventLoopGroup) = {
    val b = new ServerBootstrap()
    val httpServerHandler = new HttpServerHandler()

    val executor = Executors.newSingleThreadScheduledExecutor()
    executor.scheduleAtFixedRate(new Runnable {
      override def run(): Unit = {
      }
    }, 1, 1, TimeUnit.SECONDS)

    b.option[Integer](ChannelOption.SO_BACKLOG, 1024)
    b.group(boss, worker)
      .channel(classOf[NioServerSocketChannel])
      .childHandler(new ChannelInitializer[SocketChannel]() {
        override def initChannel(ch: SocketChannel) = {
          val p = ch.pipeline
          p.addLast(new HttpServerCodec)
          p.addLast(new HttpServerExpectContinueHandler)
          p.addLast(httpServerHandler)
        }
      })
    val ch = b.bind(8080).sync().channel()
    ch.closeFuture().sync()
  }
}

@Sharable
class HttpServerHandler() extends ChannelInboundHandlerAdapter {
  override def channelRead(ctx: ChannelHandlerContext, msg: Any): Unit = {
    if (msg.isInstanceOf[HttpRequest]) {
      val request = msg.asInstanceOf[HttpRequest]
      val keepAlive = HttpUtil.isKeepAlive(request)
      val response = new DefaultFullHttpResponse(
        HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
        Unpooled.wrappedBuffer(HttpServerHandler.Content))
      response.headers().set(HttpServerHandler.ContentType, HttpServerHandler.TextPlain)
      response.headers().setInt(HttpServerHandler.ContentLength, response.content().readableBytes())
      if (keepAlive) {
        response.headers().set(HttpServerHandler.Connection, HttpServerHandler.KeepAlive)
        ctx.write(response)
      } else {
        ctx.write(response).addListener(ChannelFutureListener.CLOSE)
      }
    }
  }

  override def channelReadComplete(ctx: ChannelHandlerContext): Unit = {
    ctx.flush()
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable): Unit = {
    ctx.close()
  }
}

object HttpServerHandler {
  val Content = "Hello, world.".toArray.map(_.toByte)
  val ContentType = AsciiString.cached("Content-Type")
  val TextPlain = AsciiString.cached("text/plain")
  val ContentLength = AsciiString.cached("Content-Length")
  val Connection = AsciiString.cached("Connection")
  val KeepAlive = AsciiString.cached("keep-alive")
}

object Main {
  def main(args: Array[String]): Unit = {
    val boss = new NioEventLoopGroup(1)
    val worker = new NioEventLoopGroup()

    new HttpServer().run(boss, worker)

    boss.shutdownGracefully()
    worker.shutdownGracefully()
  }
}
