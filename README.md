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

```
run -it --rm -v /tmp/http-rase:/http-rase -p 8080:8080 --name node node node /http-rase
[root@monaural] # /usr/local/src/wrk/wrk -t4 -c8 -d100s http://localhost:8080/
Running 2m test @ http://localhost:8080/
  4 threads and 8 connections
^C  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.51ms    2.09ms  31.67ms   93.45%
    Req/Sec     1.78k   440.75     3.64k    72.76%
  238192 requests in 33.67s, 29.76MB read
Requests/sec:   7073.77
Transfer/sec:      0.88MB

Running 10s test @ http://localhost:8080/
  8 threads and 24 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   325.03us    0.99ms  20.17ms   95.05%
    Req/Sec    23.13k     3.82k   38.23k    70.56%
  1852909 requests in 10.10s, 231.49MB read
Requests/sec: 183463.75
Transfer/sec:     22.92MB
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

Running 10s test @ http://localhost:8080/
  8 threads and 24 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   155.20us  211.03us   5.68ms   89.96%
    Req/Sec    27.28k     1.70k   35.00k    71.34%
  2187883 requests in 10.10s, 271.25MB read
Requests/sec: 216635.31
Transfer/sec:     26.86MB
```

```sh
sbt assembly && java -jar target/scala-2.12/http-race-assembly-0.1.0-SNAPSHOT.jar
```

```
Running 10s test @ http://localhost:8080/
  8 threads and 24 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   393.19us  425.36us  17.48ms   99.39%
    Req/Sec     7.94k   691.36     9.39k    89.22%
  637851 requests in 10.10s, 62.05MB read
Requests/sec:  63138.12
Transfer/sec:      6.14MB
wrk -t8 -c24 -d10s http://localhost:8080/  4.42s user 10.80s system 150% cpu 10.111 total

Running 10s test @ http://localhost:8080/
  8 threads and 24 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   661.79us    5.06ms  98.44ms   97.89%
    Req/Sec    38.57k    11.03k   62.67k    70.35%
  3093178 requests in 10.10s, 230.09MB read
Requests/sec: 306254.10
Transfer/sec:     22.78MB
```

```sh
time wrk -t8 -c24 -d10s http://localhost:8080/
time ~/wrk/wrk -t8 -c24 -d10s http://localhost:8080/
```
