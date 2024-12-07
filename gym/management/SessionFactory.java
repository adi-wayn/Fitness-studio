package gym.management;
import gym.Exception.InstructorNotQualifiedException;
import gym.management.Sessions.*;

public class SessionFactory {

    public static Session createSession(SessionType type, String date, ForumType forum, Instructor instructor) {

        if (instructor == null || !instructor.isQualifiedFor(type)) {
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");
        }

        return new Session(type, date, forum, instructor);
    }
}
