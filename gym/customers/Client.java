package gym.customers;
import gym.Gym;
import gym.management.MessageObserver;
import java.util.ArrayList;

public class Client implements MessageObserver {
    private final ArrayList<String> notifications;
    private final Person person;

    public Client(Person person) {
        this.person = person;
        this.notifications = new ArrayList<>();
    }

    public Person getPerson() {
        return person;
    }

    public String getName() {
        return this.person.getName();
    }

    public ArrayList<String> getNotifications() {
        return this.notifications;
    }

    @Override
    public void update(String message, String secretaryKey) throws SecurityException {
        String key = Gym.getInstance().getSecretary().getKey();
        if (!secretaryKey.equals(key))
            throw new SecurityException("Wrong Key,access denied");

        this.notifications.add(message);
    }

    @Override
    public String toString() {
        return "Name: " + person.getName() + " | Gender: " + person.getGender() + " | Birthday: " + person.getBirthDate() + " | Age: " + person.getAge() + " | Balance: " + person.getBalance();
    }
}