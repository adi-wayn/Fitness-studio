package gym.management.Sessions;
import gym.management.Gym;
import gym.management.Instructor;
import gym.management.RegisterClientToSession;
import gym.management.Registrable;

import java.time.*;
import java.time.format.DateTimeFormatter;

public abstract class Session implements Registrable {
    private static int sessionIdCounter = 1;
    private int sessionId;
    private final String date;
    private final ForumType forumType;
    private final Instructor instructor;


    public Session(String date, ForumType forumType,Instructor instructor) {
        this.sessionId = sessionIdCounter;
        sessionIdCounter++;
        this.date = date;
        this.forumType = forumType;
        this.instructor = instructor;
    }

    @Override
    public int getId() {
        return this.sessionId;
    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public ForumType getForumType() {
        return forumType;
    }

    public String getDate() {
        return date;
    }

    public String getSpecialDate(){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(this.date, inputFormatter);

        return dateTime.format(outputFormatter);
    }

    public boolean hasPast() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime eventDateTime = LocalDateTime.parse(this.date, formatter);


        return eventDateTime.isBefore(currentDateTime);
    }

    public abstract SessionType getSessionType();

    public abstract int getPrice();

    public abstract int getCapacity();

    @Override
    public String toString() {
        return "Session Type: " + this.getSessionType() + " | Date: " + date + " | Forum: " + forumType + " | Instructor: " + instructor.getPerson().getName() +
                " | Participants: " + RegisterClientToSession.getInstance().getClientListMap(Gym.getInstance().getSecretary().getKey()).get(this).size() + "/" + this.getCapacity();
    }

}
