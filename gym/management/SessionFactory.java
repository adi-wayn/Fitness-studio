package gym.management;
import gym.Exception.InstructorNotQualifiedException;
import gym.management.Sessions.*;

class SessionFactory {

    public static Session createSession(SessionType type, String date, ForumType forum, Instructor instructor) {

        if (instructor == null || !instructor.isQualifiedFor(type)) {
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");
        }

        switch (type) {
            case ThaiBoxing -> {
                return new ThaiBoxingSession(date, forum, instructor);
            }

            case MachinePilates -> {
                return new MachinePilatesSession(date, forum, instructor);
            }

            case Pilates -> {
                return new PilatesSession(date, forum, instructor);
            }

            case Ninja -> {
                return new NinjaSession(date, forum, instructor);
            }

            default -> {
                return null;
            }
        }
    }
}
