```sh
/usr/local/Cellar/node/10.6.0/bin/node .
```

```
Running 10s test @ http://localhost:8080/
  8 threads and 24 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   427.12us  106.35us   6.54ms   95.09%
    Req/Sec     6.99k   423.32     8.17k    81.68%
  562135 requests in 10.10s, 70.23MB read
Requests/sec:  55653.28
Transfer/sec:      6.95MB
wrk -t8 -c24 -d10s http://localhost:8080/  4.37s user 10.46s system 146% cpu 10.112 total
```

```sh
/usr/local/Cellar/go/1.10.3/bin/go run main.go
```

```
Running 10s test @ http://localhost:8080/
  8 threads and 24 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   396.31us   56.21us   1.87ms   85.67%
    Req/Sec     7.47k   254.27     8.26k    71.91%
  600352 requests in 10.10s, 74.43MB read
Requests/sec:  59441.93
Transfer/sec:      7.37MB
wrk -t8 -c24 -d10s http://localhost:8080/  4.05s user 10.55s system 144% cpu 10.115 total
```

```sh
sbt assembly && java -jar target/scala-2.12/http-race-assembly-0.1.0-SNAPSHOT.jar
```

```
Running 10s test @ http://localhost:8080/
  8 threads and 24 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   409.82us  580.72us  25.62ms   99.08%
    Req/Sec     7.92k     0.89k    8.94k    92.33%
  637055 requests in 10.10s, 61.97MB read
Requests/sec:  63074.48
Transfer/sec:      6.14MB
wrk -t8 -c24 -d10s http://localhost:8080/  4.42s user 10.80s system 150% cpu 10.111 total
```
