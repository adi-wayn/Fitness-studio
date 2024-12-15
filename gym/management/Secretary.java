package gym.management;
import gym.customers.*;
import gym.Exception.*;
import gym.management.Sessions.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Secretary implements Manageable {
    private final Person person;
    private static final Registry<Client> clientRegistry = new Registry<>();
    private static final Registry<Instructor> instructorRegistry = new Registry<>();
    private static final Registry<Session> sessionRegistry = new Registry<>();
    private static final List<String> actionPrints = new ArrayList<>();
    private final String secretaryKey = "E|Me@!(@bTx)GST.";

    public Secretary(Person person) {
        this.person = person;
    }

    public Person getPerson(){
        return this.person;
    }

    @Override
    public Registry<Client> getClientRegistry() {
        return clientRegistry;
    }

    @Override
    public Registry<Instructor> getInstructorRegistry() {
        return instructorRegistry;
    }

    @Override
    public Registry<Session> getSessionRegistry() {
        return sessionRegistry;
    }

    @Override
    public List<String> getActionPrints() {
        return actionPrints;
    }

    @Override
    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if (!isCurrentSecretary())
            return null;

        Client newClient = ClientFactory.createClient(person);
        clientRegistry.add(newClient, this.secretaryKey);
        actionPrints.add("Registered new client: " + person.getName());

        return newClient;
    }

    @Override
    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!isCurrentSecretary())
            return;

        if (!clientRegistry.isRegistered(client, this.secretaryKey))
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");

        clientRegistry.remove(client , this.secretaryKey);
        actionPrints.add("Unregistered client: " + client.getPerson().getName());
    }

    @Override
    public Instructor hireInstructor(Person person, int hourSalary, ArrayList<SessionType> sessionList) {
        if (!isCurrentSecretary())
            return null;

        Instructor instructor = InstructorFactory.createInstructor(person, hourSalary, sessionList);
        if (!instructorRegistry.isRegistered(instructor , this.secretaryKey)) {
            instructorRegistry.add(instructor , this.secretaryKey);
            actionPrints.add("Hired new instructor: " + person.getName() + " with salary per hour: " + hourSalary);
            return instructor;
        }

        return null;
    }

    @Override
    public Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        if (!isCurrentSecretary())
            return null;

        Session session = SessionFactory.createSession(sessionType, date, forumType, instructor);
        if (!sessionRegistry.isRegistered(session , this.secretaryKey)) {
            sessionRegistry.add(session , this.secretaryKey);
            RegisterClientToSession.getInstance().getClientListMap(this.secretaryKey).put(session, new HashSet<>());
            actionPrints.add("Created new session: " + sessionType + " on " + session.getSpecialDate() + " with instructor: " + instructor.getPerson().getName());
            return session;
        }

        return null;
    }

    @Override
    public void registerClientToLesson(Client c1, Session s1) throws DuplicateClientException, ClientNotRegisteredException, NullPointerException {
        if (!isCurrentSecretary())
            throw new NullPointerException();

    RegisterClientToSession.getInstance().addToMap(s1, c1 ,this.secretaryKey);
    }

    @Override
    public void notify(Session s1, String message) {
        if (!isCurrentSecretary())
            return;

        for (Client client : RegisterClientToSession.getInstance().getClientListMap(this.secretaryKey).get(s1)) {
            client.update(message, this.secretaryKey);
        }
        this.getActionPrints().add("A message was sent to everyone registered for session " + s1.getSessionType() + " on " + s1.getSpecialDate() + " : " + message);
    }

    @Override
    public void notify(String date, String message) {
        if (!isCurrentSecretary())
            return;

        for (Session session : RegisterClientToSession.getInstance().getClientListMap(this.secretaryKey).keySet()) {
            if (session.getDate().substring(0, 10).equals(date)) {
                for (Client client : RegisterClientToSession.getInstance().getClientListMap(this.secretaryKey).get(session)) {
                    client.update(message, this.secretaryKey);
                }
            }
        }
        this.getActionPrints().add("A message was sent to everyone registered for a session on " + rearrangeDate(date)  + " : " + message);
    }

    private String rearrangeDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, inputFormatter);
        return parsedDate.format(outputFormatter);
    }

    @Override
    public void notify(String message) {
        if (!isCurrentSecretary())
            return;

        for (Client client :clientRegistry.getAll(this.secretaryKey) )
            client.update(message, this.secretaryKey);

        this.getActionPrints().add("A message was sent to all gym clients: " + message);
    }

    @Override
    public void paySalaries() {
        this.person.addToBalance(Gym.getInstance().getSecretarySalary(), this.secretaryKey);
        Gym.getInstance().subtractFromGymBalance(Gym.getInstance().getSecretarySalary());

        if (!isCurrentSecretary())
            return;

        for (Session session : sessionRegistry.getAll(this.secretaryKey)) {
            session.getInstructor().getPerson().addToBalance(session.getInstructor().getHourSalary(), this.secretaryKey);
            Gym.getInstance().subtractFromGymBalance(session.getInstructor().getHourSalary());
        }

        this.getActionPrints().add("Salaries have been paid to all employees");

   }

   @Override
    public void printActions() {
        if (!isCurrentSecretary())
            return;

        for (String string: actionPrints){
            System.out.println(string);
        }
    }

    private boolean isCurrentSecretary() {
        return Gym.getInstance().getSecretary().equals(this);
    }

    @Override
    public String getKey(){
        if (!isCurrentSecretary())
            return null;

        return this.secretaryKey;
    }

    @Override
    public String toString() {
        return "ID: " + person.getId() + " | Name: " + person.getName() + " | Gender: " + person.getGender() + " | Birthday: " + person.getBirthDate() + " | Age: " + person.getAge() + " | Balance: " + person.getBalance() +
                " | Role: Secretary | Salary per Month: " + Gym.getInstance().getSecretarySalary();
    }
}