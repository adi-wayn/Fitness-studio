package gym.management;
import gym.customers.Client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    public boolean isInstructorRegistered(Instructor instructor) {
        return instructors.contains(instructor);
    }

    public List<Instructor> getAllInstructors() {
        return new ArrayList<>(instructors);
    }

    @Override
    public String toString() {
        StringBuilder instructorsData = new StringBuilder();

        for (Instructor instructor : getAllInstructors())
            instructorsData.append(instructor).append("\n");

        return instructorsData.toString();
    }
}
