package network.tcp.v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class ClientV2 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");
        // 네트워크 연결하려면 소켓이 필요
        Socket socket = new Socket("localhost", PORT); // 소켓 연결 - 얘가 tcp/ip 연결 다 해줌

        // 보조 스트림 이용해서 외부에 데이터 주고받기
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        log("소켓 연결: " + socket);

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("전송 문자: ");
            // 서버에 문자 보내기
            String toSend = scanner.nextLine();
            output.writeUTF(toSend);
            log("client -> server: " + toSend);
            if(toSend.equals("exit")) {
                break;
            }
            // 서버로부터 문자 받기
            String received = input.readUTF();
            log("server -> client: " + received);
        }


        // 자원 정리
        log("연결 종료: " + socket);
        input.close();
        output.close();
        socket.close();

    }

}
