package gym;
import gym.management.ClientRegistry;
import gym.management.InstructorRegistry;
import gym.management.Secretary;
import gym.customers.Person;
import gym.management.SessionRegistry;

public class Gym {
    private static final Gym gym = new Gym();
    private String name;
    private Secretary secretary;
    private int gymBalance;
    private int secretarySalary;

    private Gym() {
    }

    public static Gym getInstance() {
        return gym;
    }

    public int getSecretarySalary() {
        return secretarySalary;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void setSecretary(Person secretary, int salary) {
        this.secretary = new Secretary(secretary);
        this.secretary.getActionPrints().add("A new secretary has started working at the gym: " + this.secretary.getPerson().getName());
        setSalary(salary);
    }

    public void setSalary(int secretarySalary) {
        this.secretarySalary = secretarySalary;
    }

    public Secretary getSecretary() {
        return this.secretary;
    }

    public int getGymBalance() {
        return this.gymBalance;
    }

    public void addToGymBalance(int amount) {
        this.gymBalance += amount;
    }

    @Override
    public String toString() {
        return "Gym Name: " + name + "\n" +
                "Gym Secretary: " + secretary + "\n" +
                "Gym Balance: " + gymBalance + "\n" +
                "\n" +
                "Clients Data:" + "\n" +
                ClientRegistry.getInstance() +
                "\n" +
                "Employees Data:" + "\n" +
                InstructorRegistry.getInstance() +
                secretary + "\n" +
                "\n" +
                "Sessions Data:" + "\n" +
                SessionRegistry.getInstance();

    }
}
