package network.tcp.chat.server.command;

import network.tcp.chat.server.Session;
import network.tcp.chat.server.SessionManager;

import java.io.IOException;

public class MessageCommand implements Command{
    private final SessionManager sessionManager;

    public MessageCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {
        String message = args[1];
        // [사용자 이름] 메시지
        sessionManager.sendAll("[" + session.getUsername() + "] " + message);
    }
}
