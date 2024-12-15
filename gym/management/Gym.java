package gym.management;
import gym.customers.Person;

public class Gym {
    private static Gym gym;
    private String name;
    private Secretary secretary;
    private int gymBalance;
    private int secretarySalary;

    private Gym() {
    }

    public static Gym getInstance() {
        if (gym == null) {
            gym = new Gym();
        }

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

    public void addToGymBalance(int amount) {
        this.gymBalance += amount;
    }

    public void subtractFromGymBalance(int amount) {
        this.gymBalance -= amount;
    }

    @Override
    public String toString() {
        return "Gym Name: " + name + "\n" +
                "Gym Secretary: " + secretary + "\n" +
                "Gym Balance: " + gymBalance + "\n" +
                "\n" +
                "Clients Data:" + "\n" +
                this.secretary.getClientRegistry() +
                "\n" + "\n" +
                "Employees Data:" + "\n" +
                this.secretary.getInstructorRegistry() +
                "\n"+
                secretary + "\n" +
                "\n" +
                "Sessions Data:" + "\n" +
                this.secretary.getSessionRegistry();

    }
}