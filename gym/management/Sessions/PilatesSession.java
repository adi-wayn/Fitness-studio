package gym.management.Sessions;
import gym.management.Instructor;

public class PilatesSession extends Session {
    private static final SessionType type = SessionType.Pilates;
    private static final int price = 60;
    private static final int capacity = 30;

    public PilatesSession(String date, ForumType forumType, Instructor instructor) {
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
