package gym.management;

public interface MessageObserver {
    void update(String message, String secretaryKey) throws SecurityException;
}
