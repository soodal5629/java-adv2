package network.tcp.chat;

import java.util.ArrayList;
import java.util.List;

public class ChatSessionManager {
    private List<ChatSession> sessions = new ArrayList<>();

    public synchronized void add(ChatSession session) {
        sessions.add(session);
    }
    public synchronized void remove(ChatSession session) {
        sessions.remove(session);
    }

    public synchronized void closeAll() {
        for(ChatSession session : sessions) {
            session.close();
        }
        sessions.clear();
    }

    public synchronized List<ChatSession> getSessions() {
        return sessions;
    }

    public synchronized List<String> getUserNameList() {
        List<String> userNameList = new ArrayList<>();
        for(ChatSession session : sessions) {
            userNameList.add(session.getUserName());
        }
        return userNameList;
    }

}
