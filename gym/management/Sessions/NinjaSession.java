package gym.management.Sessions;

import gym.management.Instructor;

public class NinjaSession extends Session {
    private static final SessionType type = SessionType.Ninja;
    private static final int price = 150;
    private static final int capacity = 5;

    public NinjaSession(String date, ForumType forumType, Instructor instructor) {
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
