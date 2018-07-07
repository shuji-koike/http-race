package main

import (
  "fmt"
  "net/http"
)

func main() {
  http.HandleFunc("/", func (res http.ResponseWriter, req *http.Request) {
    fmt.Fprintf(res, "Hello, world.")
  })
  if err := http.ListenAndServe(":8080", nil); err != nil {
    panic(err)
  }
}
