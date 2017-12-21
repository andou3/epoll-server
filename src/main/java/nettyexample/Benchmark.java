package nettyexample;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Benchmark {

    final private static String URL = "http://127.0.0.1:8081/hello";

    public Benchmark(int size) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(size);
        threadPool.scheduleAtFixedRate(() -> {
            String result = "";
            int code;
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(URL).openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                code = connection.getResponseCode();
                if (code == 200) {
                    result = "SUCCESS on thread #" + Thread.currentThread().getId();
                }
            } catch (IOException e) {
                result = ">>FAILURE<<";
            }
            System.out.println("\t\tStatus: " + result);
        }, 0, 30, TimeUnit.MILLISECONDS);
    }
}
