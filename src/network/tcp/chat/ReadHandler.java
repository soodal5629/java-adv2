package network.tcp.chat;

import java.io.DataInputStream;
import java.io.IOException;

import static util.MyLogger.log;

public class ReadHandler implements Runnable {
    private final DataInputStream input;
    private final ChatClient client;
    public boolean closed = false;

    public ReadHandler(DataInputStream input, ChatClient client) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while(true) {
                // 서버로부터 문자 받기
                String received = input.readUTF();
                System.out.println("received = " + received);
            }
        } catch (IOException e) {
            log(e);
        } finally {
            // client 에서 socket, ReadHandler, WriterHandler 모두 자원 정리
            client.close();
        }
    }

    public synchronized void close() {
        // 동시 호출 방지
        if(closed) {
            return;
        }
        // 종료 로직 필요시 작성
        closed = true;
        log("ReadHandler 종료");
    }
}
