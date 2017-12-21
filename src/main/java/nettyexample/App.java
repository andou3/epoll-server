package nettyexample;

import nettyexample.server.WebServer;

public class App {
    public static void main(final String[] args) throws Exception {
        Benchmark benchmark = new Benchmark(3);
        new WebServer()

                // Simple GET request
                .get("/hello", (request, response) -> "Hello world")

                // Simple POST request
                .post("/hello", (request, response) ->
                    "Hello world: " + request.body()
                )
                // Start the server
                .start();
    }
}
