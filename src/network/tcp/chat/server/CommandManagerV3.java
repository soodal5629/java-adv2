package network.tcp.chat.server;

import network.tcp.chat.server.command.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 디자인 패턴 중 커맨드 패턴에 해당함
 */
public class CommandManagerV3 implements CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private static final String DELIMITER = "\\|";

    public CommandManagerV3(SessionManager sessionManager) {
        commands.put("/join", new JoinCommand(sessionManager));
        commands.put("/message", new MessageCommand(sessionManager));
        commands.put("/change", new ChangeCommand(sessionManager));
        commands.put("/users", new UsersCommand(sessionManager));
        commands.put("/exit", new ExitCommand());
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {
        String[] args = totalMessage.split(DELIMITER);
        // /join /message /change /users /exit
        String key = args[0];
        // 다형성 활용
        Command command = commands.get(key);
        if(command == null) { // 잘못된 명령어
            session.send("처리할 수 없는 명령어 입니다: " + totalMessage);
            return;
        }
        command.execute(args, session);
    }
}
