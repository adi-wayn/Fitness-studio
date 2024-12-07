package gym.management;

public interface Observer {
    void update(String message, String secretaryKey) throws SecurityException;
}
