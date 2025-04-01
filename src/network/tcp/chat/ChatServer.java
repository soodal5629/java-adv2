package network.tcp.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ChatServer {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("chat server 시작");
        ChatSessionManager sessionManager = new ChatSessionManager();
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        try {
            while(true) {
                Socket socket = serverSocket.accept();
                log("소켓 연결 : " + socket);

                ChatSession chatSession = new ChatSession(socket, sessionManager);
                Thread thread = new Thread(chatSession);
                thread.start();
            }
        } catch (IOException e) {
            log("서버 소켓 종료: " + e);
        }
    }
}
