package gym.management;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gym.management.Sessions.*;

public class SessionRegistry {
    private static SessionRegistry instance;
    private final Set<Session> sessions;

    private SessionRegistry() {
        sessions = new HashSet<>();
    }

    public static SessionRegistry getInstance() {
        if (instance == null) {
            instance = new SessionRegistry();
        }
        return instance;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public boolean isSessionRegistered(Session session) {
        return sessions.contains(session);
    }

    public List<Session> getAllSessions() {
        return new ArrayList<>(sessions);
    }

    public List<Session> getSessionsByType(SessionType type) {
        List<Session> result = new ArrayList<>();
        for (Session session : sessions) {
            if (session.getSessionType() == type) {
                result.add(session);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sessionsData = new StringBuilder();

        for (Session session : getAllSessions())
            sessionsData.append(session).append("\n");

        return sessionsData.toString();
    }
}
