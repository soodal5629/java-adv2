package network.exception.connect;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SoTimeoutServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(12345);
        Socket socket = serverSocket.accept();
        // 응답 안준다고 가정
        Thread.sleep(1000000);
    }
}
