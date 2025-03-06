package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectTimeoutMain2 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        try {
            // 빈 소켓 객체 만들면 연결 시도 x
            Socket socket = new Socket();
            // 연결 후 3초의 타임아웃 - SocketTimeoutException 발생
            socket.connect(new InetSocketAddress("192.168.1.250", 45678), 3000);
            long end = System.currentTimeMillis();
            System.out.println("end = " + (end - start));
        } catch (ConnectException e) {
            e.printStackTrace();
        }

    }
}
