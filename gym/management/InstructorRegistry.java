package gym.management;
import gym.Gym;
import gym.customers.Person;

import java.util.*;

public class InstructorRegistry {
    private static InstructorRegistry instance;
    private final Set<Instructor> instructors;

    private InstructorRegistry() {
        instructors = new HashSet<>();
    }

    public static InstructorRegistry getInstance() {
        if (instance == null) {
            instance = new InstructorRegistry();
        }
        return instance;
    }

    public void addInstructor(Instructor instructor, String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        this.instructors.add(instructor);
    }

    public boolean isInstructorRegistered(Instructor instructor, String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        return instructors.contains(instructor);
    }

    public List<Instructor> getAllInstructors(String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        return new ArrayList<>(instructors);
    }

    @Override
    public String toString() {
        StringBuilder instructorsData = new StringBuilder();
        List<Instructor> sortedList =  getAllInstructors(Gym.getInstance().getSecretary().getKey());
        sortedList.sort(Comparator.comparingInt(Instructor::getId));

        for (Instructor instructor : sortedList)
            instructorsData.append(instructor).append("\n");

        return instructorsData.toString();
    }
}
