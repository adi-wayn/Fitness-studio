package gym.management;
import java.util.*;

import gym.Gym;
import gym.customers.Client;
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

    public void addSession(Session session , String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        sessions.add(session);
    }

    public boolean isSessionRegistered(Session session, String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        return sessions.contains(session);
    }

    public List<Session> getAllSessions(String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        return new ArrayList<>(sessions);
    }


    @Override
    public String toString() {
        StringBuilder sessionsData = new StringBuilder();

        List<Session> sortedList =  getAllSessions(Gym.getInstance().getSecretary().getKey());
        sortedList.sort(Comparator.comparingInt(Session::getSessionId));

        for (Session session : sortedList)
            sessionsData.append(session).append("\n");

        return sessionsData.toString();
    }
}
