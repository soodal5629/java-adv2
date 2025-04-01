package network.tcp.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class ChatClient {
    private final String host;
    private final int port;

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    private ReadHandler readHandler;
    private WriterHandler writerHandler;
    private boolean closed = false;

//    private static final int PORT = 12345;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        log("클라이언트 시작");
        socket = new Socket(host, port);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());

        readHandler = new ReadHandler(input, this);
        writerHandler = new WriterHandler(output, this);

        Thread readThread = new Thread(readHandler, "readHandler");
        Thread wrtieThread = new Thread(writerHandler, "writerHandler");

        readThread.start();
        wrtieThread.start();

    }

    // 내가 짠 코드
//    public static void main(String[] args) throws IOException, InterruptedException {
//
//        try (Socket socket = new Socket("localhost", PORT);
//             DataInputStream input = new DataInputStream(socket.getInputStream());
//             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
//            log("채팅 소켓 연결: " + socket);
//            // 채팅 메시지 송.수신 분리
//            ReadHandler readHandler = new ReadHandler(input);
//            WriterHandler writerHandler = new WriterHandler(output);
//            Thread clientReadThread = new Thread(readHandler, "clientReadHandler");
//            Thread clientWriteThread = new Thread(writerHandler, "clientWriteHandler");
//
//            clientWriteThread.start();
//            clientReadThread.start();
//
//            clientWriteThread.join();
//            clientReadThread.join();
//        }
//
//    }

    public synchronized void close() {
        if(closed) {
            return;
        }
        writerHandler.close();
        readHandler.close();
        closeAll(socket, input, output);
        closed = true;
        log("연결 종료: " + socket);
    }
}
