package edu.eci.arep;

import java.io.IOException;

/**
 * Example application demonstrating how to use the web framework.
 *
 *   http://localhost:8080/index.html
 *   http://localhost:8080/hello?name=Pedro
 *   http://localhost:8080/pi
 */
public class App {

    public static void main(String[] args) throws IOException {
        staticfiles("/webroot");
        get("/hello", (req, resp) -> "Hello " + req.getValues("name"));
        get("/pi", (req, resp) -> String.valueOf(Math.PI));
        start();
    }

    private static void staticfiles(String location) {
        WebFramework.staticfiles(location);
    }

    private static void get(String path, Route handler) {
        WebFramework.get(path, handler);
    }

    private static void start() throws IOException {
        WebFramework.start();
    }
}
