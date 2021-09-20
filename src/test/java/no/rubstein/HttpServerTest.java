package no.rubstein;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpServerTest {
    @Test
    void shouldReadResponseCode() throws IOException {
        HttpServer server = new HttpServer(500);
        server.start();
        int port = server.getActualPort();
        System.out.println(port);
        HttpClient client = new HttpClient("localhost", port, "/hello");

        assertEquals(200, client.getResponseCode());
    }
}