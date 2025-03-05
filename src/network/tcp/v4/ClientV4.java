package network.tcp.v4;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class ClientV4 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");
        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;
        try {
            // 네트워크 연결하려면 소켓이 필요
            socket = new Socket("localhost", PORT); // 소켓 연결 - 얘가 tcp/ip 연결 다 해줌

            // 보조 스트림 이용해서 외부에 데이터 주고받기
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            log("소켓 연결: " + socket);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("전송 문자: ");
                // 서버에 문자 보내기
                String toSend = scanner.nextLine();
                output.writeUTF(toSend);
                log("client -> server: " + toSend);
                if (toSend.equals("exit")) {
                    break;
                }
                // 서버로부터 문자 받기
                String received = input.readUTF();
                log("server -> client: " + received);
            }
        } catch(IOException e) {
            log(e);
        } finally {
            // 자원 정리
            closeAll(socket, input, output);
            log("연결 종료: " + socket);
        }
    }

}
