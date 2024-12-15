package gym.management;
import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.*;
import java.util.ArrayList;
import java.util.List;

interface Manageable {

    Registry<Client> getClientRegistry();

    Registry<Instructor> getInstructorRegistry();

    Registry<Session> getSessionRegistry();

    List<String> getActionPrints();

    Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException;

    void unregisterClient(Client client) throws ClientNotRegisteredException;

    Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException;

    Instructor hireInstructor(Person person, int hourSalary, ArrayList<SessionType> sessionList);

    void registerClientToLesson(Client c1, Session s1) throws DuplicateClientException, ClientNotRegisteredException;

    void notify(Session s1, String message);

    void notify(String date, String message);

    void notify(String message);

    void paySalaries();

    void printActions();

    String getKey();
}


