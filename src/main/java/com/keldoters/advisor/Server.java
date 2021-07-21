package com.keldoters.advisor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;


public class Server {

    public static String accessServer = "https://accounts.spotify.com";
    public static HttpServer server;
    public static String redirectUri = "http://localhost:8080";

    public static void setLocalServer() throws IOException {
        server = HttpServer.create();
        server.bind(new InetSocketAddress(8080),0);
        server.start();

    }
    public static void stopServer() {
        server.stop(10);
    }
    public static void getAccessCode(Auth user) {
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                String context = httpExchange.getRequestURI().getQuery();
                if (context != null && context.contains("code")) {
                    String code = context.substring(5);
                    user.setCode(code);
                    context = "Got the code. Return back to your program.";
                } else {
                    context = "Authorization code not found. Try again.";
                }
                httpExchange.sendResponseHeaders(200,context.length());
                httpExchange.getResponseBody().write(context.getBytes());
                httpExchange.getResponseBody().close();
            }
        });
    }
}
