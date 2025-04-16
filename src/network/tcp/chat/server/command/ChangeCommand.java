package network.tcp.chat.server.command;

import network.tcp.chat.server.Session;
import network.tcp.chat.server.SessionManager;

import java.io.IOException;

public class ChangeCommand implements Command {
    private final SessionManager sessionManager;

    public ChangeCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {
        String changeUsername = args[1];
        sessionManager.sendAll(session.getUsername() + "님이 " + changeUsername + "로 이름을 변경했습니다.");
    }
}
