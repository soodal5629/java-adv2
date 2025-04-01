package network.tcp.chat;

import java.io.DataInputStream;
import java.io.IOException;

import static util.MyLogger.log;

public class ReadHandler implements Runnable {
    private final DataInputStream input;
    public ReadHandler(DataInputStream input) {
        this.input = input;
    }

    @Override
    public void run() {
        while(true) {
            // 서버로부터 문자 받기
            String received = null;
            try {
                received = input.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            log("server -> client: " + received);
        }
    }
}
