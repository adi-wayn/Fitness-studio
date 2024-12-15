package gym.management.Sessions;

import gym.management.Instructor;

public class ThaiBoxingSession extends Session {
    private static final SessionType type = SessionType.ThaiBoxing;
    private static final int price = 100;
    private static final int capacity = 20;

    public ThaiBoxingSession(String date, ForumType forumType, Instructor instructor) {
        super(date, forumType, instructor);
    }

    @Override
    public SessionType getSessionType() {
        return type;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }
}
