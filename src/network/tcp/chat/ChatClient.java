package network.tcp.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class ChatClient {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException, InterruptedException {

        try (Socket socket = new Socket("localhost", PORT);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
            log("채팅 소켓 연결: " + socket);
            // 채팅 메시지 송.수신 분리
            ReadHandler readHandler = new ReadHandler(input);
            WriterHandler writerHandler = new WriterHandler(output);
            Thread clientReadThread = new Thread(readHandler, "clientReadHandler");
            Thread clientWriteThread = new Thread(writerHandler, "clientWriteHandler");

            clientWriteThread.start();
            clientReadThread.start();

            clientWriteThread.join();
            clientReadThread.join();
        }

    }
}
