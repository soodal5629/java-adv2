package network.exception.close.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static util.MyLogger.log;

public class ResetCloseClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결 " + socket);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // client <- server: FIN 날아옴
        Thread.sleep(1000); // 서버가 close()호출할 때까지 잠시 대기

        // client -> server: PUSH[1] (서버로 데이터 전송)
        // TCP 원칙은 상대방이 FIN을 날리면 수신자도 FIN을 보내야지 다른 것을 보내면 안됨 -> TCP 규약 벗어남 -> 거절됨
        output.write(1);

        // client <- server: RST 패킷
        Thread.sleep(1000); // RST 메시지 전송 대기
        try {
            int read = input.read();
        } catch(SocketException e) {
            e.printStackTrace();
        }

        try {
            output.write(1);
        } catch(SocketException e) {
            e.printStackTrace();
        }

    }
}
