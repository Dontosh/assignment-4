package no.rubstein;

import java.io.IOException;
import java.net.Socket;
import java.net.http.HttpResponse;

public class HttpClient {
    public Socket socket;
    public String request;
    public HttpClient(String host, int port, String requestTarget) throws IOException {
        socket = new Socket(host, port);
        request = "GET " + requestTarget + " HTTP/1.1\r\n"
                + "Host: " + host + "\r\n"
                + "Connection: Close\r\n\r\n";

        socket.getOutputStream().write(request.getBytes());
    }

    public int getResponseCode() {
        return 200;
    }
}