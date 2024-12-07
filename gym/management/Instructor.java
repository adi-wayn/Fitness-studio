package gym.management;
import gym.customers.Person;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.util.List;

public class Instructor {
    private final Person person;
    private int hourSalary;
    private List<SessionType> sessionList;


    public Instructor(Person person, int hourSalary, List<SessionType> sessionList) {
        this.person = person;
        this.hourSalary = hourSalary;
        this.sessionList = sessionList;
    }

    public Person getPerson() {
        return person;
    }

    public int getHourSalary() {
        return hourSalary;
    }

    public List<SessionType> getSessionList() {
        return sessionList;
    }

    public boolean isQualifiedFor(SessionType type) {
        return this.getSessionList().contains(type);
    }

    public int getId(){
        return this.person.getId();
    }

    @Override
    public String toString() {
        StringBuilder certifiedClasses = new StringBuilder();

        for (SessionType sessionType : sessionList) {
            if (!sessionType.equals(sessionList.getLast()))
                certifiedClasses.append(sessionType).append(", ");
            else
                certifiedClasses.append(sessionType);
        }

        return "ID: " + person.getId() + " | Name: " + person.getName() + " | Gender: " + person.getGender() + " | Birthday: " + person.getBirthDate() + " | Age: " + person.getAge() + " | Balance: " + person.getBalance() +
        " | Role: Instructor | Salary per Hour: " + hourSalary + " | Certified Classes: " + certifiedClasses;
    }
}
