package network.tcp.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class ChatSession implements Runnable {
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final ChatSessionManager sessionManager;
    private boolean closed = false;
    private String userName;

    public ChatSession(Socket socket, ChatSessionManager sessionManager) throws IOException {
        this.socket = socket;
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
    }

    @Override
    public void run() {
        try {
            while(true) {
                String received = input.readUTF();
                log("client -> server: " + received);
                if (received.startsWith("/join") || received.startsWith("/change")) {
                    String[] split = received.split("\\|");
                    System.out.println("received = " + received);
                    System.out.println("split = " + split[0] + ", " + split[1]);
                    String name = split[1];
                    this.userName = name;
                } else if (received.startsWith("/message")) {
                    List<ChatSession> sessions = sessionManager.getSessions();
                    String[] split = received.split("\\|");
                    System.out.println("split = " + split[0] + ", " + split[1]);
                    String toSend = split[1];
                    // 모든 클라이언트에게 문자 보내기
                    for (ChatSession session : sessions) {
                        DataOutputStream sessionOutput = session.output;
                        sessionOutput.writeUTF(toSend);
                        //log("client <- server: " + toSend);
                    }
                } else if (received.startsWith("/users")) {
                    List<String> userNameList = sessionManager.getUserNameList();
                    List<ChatSession> sessions = sessionManager.getSessions();
                    // 모든 클라이언트에게 전체 사용자 목록 보내기
                    for (ChatSession session : sessions) {
                        DataOutputStream sessionOutput = session.output;
                        sessionOutput.writeUTF(String.join(",", userNameList));
                    }
                } else { // exit
                    sessionManager.remove(this);
                    break;
                }
            }
        } catch (IOException e) {
            log(e);
        } finally {
            this.close();
        }

    }

    public synchronized void close() {
        if(closed) {
            return;
        }
        closeAll(socket, input, output);
        closed = true;
        log("연결 종료: " + socket);
    }

    public String getUserName() {
        return userName;
    }
}
