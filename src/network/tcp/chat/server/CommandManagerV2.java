package network.tcp.chat.server;

import java.io.IOException;
import java.util.List;

public class CommandManagerV2 implements CommandManager {
    private final SessionManager sessionManager;
    private static final String DELIMITER = "\\|";

    public CommandManagerV2(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {
        // /join|{username}
        if(totalMessage.startsWith("/join")) {
            String[] split = totalMessage.split(DELIMITER);
            String username = split[1];
            session.setUsername(username);
            sessionManager.sendAll(username + "님이 입장했습니다.");
        } else if(totalMessage.startsWith("/message")) {
            // 클라이언트 전체에게 문자 보내기
            // /message|{내용}
            String[] split = totalMessage.split(DELIMITER);
            String message = split[1];
            // [사용자 이름] 메시지
            sessionManager.sendAll("[" + session.getUsername() + "] " + message);
        } else if(totalMessage.startsWith("/change")) {
            // /change|{이름}
            String[] split = totalMessage.split(DELIMITER);
            String changeUsername = split[1];
            sessionManager.sendAll(session.getUsername() + "님이 " + changeUsername + "로 이름을 변경했습니다.");
            session.setUsername(changeUsername);
        } else if(totalMessage.startsWith("/users")) {
            List<String> usernames = sessionManager.getAllUserName();
            StringBuilder sb = new StringBuilder();
            sb.append("전체 접속자 : ").append(usernames.size()).append("\n");
            for (String username : usernames) {
                sb.append(" - " ).append(username).append("\n");
            }
            // 요청한 클라이언트에게만 메시지 전송
            session.send(sb.toString());
        } else if(totalMessage.startsWith("/exit")) {
            throw new IOException("exit"); // 해당 예외 터뜨려서 자원 정리하도록 의도
        } else {
            session.send("처리할 수 없는 명령어 입니다: " + totalMessage);
        }
    }
}
