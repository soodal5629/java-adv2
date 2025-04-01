package network.tcp.chat.server;

import network.tcp.chat.ChatSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.MyLogger.log;

public class SessionManager {
    private List<Session> sessions = new ArrayList<>();

    public synchronized void add(Session session) {
        sessions.add(session);
    }
    public synchronized void remove(Session session) {
        sessions.remove(session);
    }

    public synchronized void closeAll() {
        for(Session session : sessions) {
            session.close();
        }
        sessions.clear();
    }

    public synchronized List<Session> getSessions() {
        return sessions;
    }

    public synchronized List<String> getAllUserName() {
        List<String> usernames = new ArrayList<>();
        for(Session session : sessions) {
            if(session.getUsername() != null) {
                usernames.add(session.getUsername());
            }
        }
        return usernames;
    }

    public synchronized void sendAll(String message) {
        for (Session session : sessions) {
            try {
                session.send(message);
            } catch (IOException e) {
                // 모든 클라이언트에게 차례로 메시지 보내다가 도중에 에러가 나도 멈추지 않도록 로그만 찍기
                log(e);
            }
        }
    }
}
