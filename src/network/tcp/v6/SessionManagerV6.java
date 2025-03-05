package network.tcp.v6;

import java.util.ArrayList;
import java.util.List;

// 서버 정상적으로 종료 시 모든 자원 정리
// 동시성 처리
public class SessionManagerV6 {
    // 모든 세션 관리
    private List<SessionV6> sessions = new ArrayList<>();

    public synchronized void add(SessionV6 session) {
        sessions.add(session);
    }

    public synchronized void remove(SessionV6 session) {
        sessions.remove(session);
    }

    public synchronized void closeAll() {
        for (SessionV6 session : sessions) {
            session.close();
        }
        sessions.clear();
    }
}
