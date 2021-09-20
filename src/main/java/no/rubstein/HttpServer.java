package no.rubstein;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer extends Thread {

    int port = 0;
    Socket clientSocket = new Socket();
    public HttpServer(int port) {
        this.port = port;
    }

    public void start() {
        new Thread(this::startServer).start();
        }

    public String readLine(Socket clientSocket) throws IOException {
        int c;
        StringBuilder stringBuilder = new StringBuilder();
        while ((c = clientSocket.getInputStream().read()) != -1 && c != '\r' ) {
            stringBuilder.append((char)c);
        }
        int expectedLine = clientSocket.getInputStream().read();
        assert expectedLine == '\n';
        return stringBuilder.toString();
    }

    public int getActualPort() {
        return port;
    }

    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            String clientRequest;
            while (!(clientRequest = readLine(clientSocket)).isBlank()) {
                System.out.println(clientRequest);
            }
            String messageBody = "Hello world!";
            String response = "HTTP / 200 OK\r\n" +
                    "Content-Length: " + messageBody.length() + "\r\n\r\n" +
                    messageBody;

            clientSocket.getOutputStream().write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}