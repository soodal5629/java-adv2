package network.tcp.chat.server.command;

import network.tcp.chat.server.Session;
import network.tcp.chat.server.SessionManager;

import java.io.IOException;
import java.util.List;

public class UsersCommand implements Command {
    private final SessionManager sessionManager;

    public UsersCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {
        List<String> usernames = sessionManager.getAllUserName();
        StringBuilder sb = new StringBuilder();
        sb.append("전체 접속자 : ").append(usernames.size()).append("\n");
        for (String username : usernames) {
            sb.append(" - " ).append(username).append("\n");
        }
        // 요청한 클라이언트에게만 메시지 전송
        session.send(sb.toString());
    }
}
