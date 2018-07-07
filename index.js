const cluster = require('cluster')
const http = require('http')

if (0 && cluster.isMaster) {
  for (let i = 0; i < require('os').cpus().length; i++) {
    cluster.fork();
  }
} else {
  http.createServer((req, res) => {
    res.write("Hello, world.")
    res.end()
  }).listen(8080)
}
