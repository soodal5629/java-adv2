package network.tcp.chat.server;

import java.io.IOException;

public class CommandManagerV1 implements CommandManager {
    private final SessionManager sessionManager;

    public CommandManagerV1(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {
        if(totalMessage.startsWith("/exit")) {
            throw new IOException("exit"); // 해당 예외 터뜨려서 자원 정리하도록 의도
        }

        sessionManager.sendAll(totalMessage);
    }
}
