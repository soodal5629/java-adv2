package network.tcp.chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static util.MyLogger.log;

public class WriterHandler implements Runnable {
    private static final String DELIMITER = "|";
    private final DataOutputStream output;
    private final ChatClient client;
    private boolean closed = false;

    public WriterHandler(DataOutputStream output, ChatClient client) {
        this.output = output;
        this.client = client;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            String username = inputUsername(scanner);
            output.writeUTF("/join" + DELIMITER + username);

            while(true) {
                String toSend = scanner.nextLine(); // 블로킹
                if(toSend.isEmpty()) {
                    continue;
                }
                if(toSend.equals("/exit")) {
                    output.writeUTF(toSend); // 서버에도 메시지 보내긴 함
                    break;
                }
                // "/"로 시작하는 명령어, 나머지는 일반 메시지
                if(toSend.startsWith("/")) { // { /user, /change }

                } else { // hihi hello 등 / 없이 입력받았을 경우 그냥 메시지로 간주하고 보냄
                    output.writeUTF("/message" + DELIMITER + toSend);
                }
                output.writeUTF(toSend);
                log("client -> server: " + toSend);
                if (toSend.equals("exit")) {
                    break;
                }
            }

        } catch (IOException |NoSuchElementException e) {
            log(e);
        } finally {
            client.close();
        }
    }

    private String inputUsername(Scanner scanner) throws IOException {
        System.out.print("이름을 입력하세요.");
        String username;
        do {
            username = scanner.nextLine();
        } while(username.isEmpty());
        return username;
    }

    public synchronized void close() {
        if (closed) {
            return;
        }
        try {
            System.in.close(); // Scanner 입력 중지(닫아줌). 사용자의 입력을 닫음
        } catch (IOException e) {
            log(e);
        }
        closed = true;
        log("WriterHandler 종료");
    }
}
