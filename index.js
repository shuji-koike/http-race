const cluster = require('cluster')
const http = require('http')

if (cluster.isMaster) {
  for (let i = 0; i < require('os').cpus().length; i++) {
    cluster.fork();
  }
} else {
  http.createServer((req, res) => {
    res.sendDate = false
    res.write("Hello, world.")
    res.end()
  }).listen(8080)
}
