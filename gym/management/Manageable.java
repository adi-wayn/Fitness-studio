package gym.management;
import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.*;

import java.util.ArrayList;

public interface Manageable {

    Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException;

    void unregisterClient(Client client) throws ClientNotRegisteredException;

    Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException;

    Instructor hireInstructor(Person person, int hourSalary, ArrayList<SessionType> sessionList);

    void registerClientToLesson(Client c1, Session s1) throws DuplicateClientException, ClientNotRegisteredException;
}


