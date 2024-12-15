package gym.management.Sessions;
import gym.management.Instructor;

public class MachinePilatesSession extends Session {
    private static final SessionType type = SessionType.MachinePilates;
    private static final int price = 80;
    private static final int capacity = 10;

    public MachinePilatesSession(String date, ForumType forumType, Instructor instructor) {
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
