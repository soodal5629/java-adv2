package network.tcp.chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static util.MyLogger.log;

public class WriterHandler implements Runnable {
    private final DataOutputStream output;
    public WriterHandler(DataOutputStream output) {
        this.output = output;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("전송 문자: ");
            // 서버에 문자 보내기
            String toSend = scanner.nextLine();
            try {
                output.writeUTF(toSend);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            log("client -> server: " + toSend);
            if (toSend.equals("exit")) {
                break;
            }
        }
    }
}
