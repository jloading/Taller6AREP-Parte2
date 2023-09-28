package org.example;

import java.io.IOException;

import static spark.Spark.*;

public class LogService {
    public static void main(String[] args) {
        port(getPort());
        get("log", (req, res) -> {
            String msg = "";
            return logMessage(msg);
        });
    }

    private static String logMessage(String msg) throws IOException {
        return HttpConnectionExample.getUrlResponse("http://localhost:4568/log?message=" + msg);
    }


    private static int getPort() {
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }
}