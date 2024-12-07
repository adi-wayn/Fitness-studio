package gym.management.Sessions;
import gym.Gym;
import gym.management.Instructor;
import gym.management.RegisterClientToSession;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Session {
    private static int sessionIdCounter = 1;
    private int sessionId;
    private final SessionType sessionType;
    private final String date;
    private final ForumType forumType;
    private final Instructor instructor;

    public Session(SessionType sessionType, String date, ForumType forumType,Instructor instructor) {
        this.sessionId = sessionIdCounter;
        sessionIdCounter++;
        this.sessionType = sessionType;
        this.date = date;
        this.forumType = forumType;
        this.instructor = instructor;
    }

    public int getSessionId() {
        return this.sessionId;
    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public SessionType getSessionType() {
        return sessionType;
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

    @Override
    public String toString() {
        return "Session Type: " + sessionType + " | Date: " + date + " | Forum: " + forumType + " | Instructor: " + instructor.getPerson().getName() +
                " | Participants: " + RegisterClientToSession.getInstance().getClientListMap(Gym.getInstance().getSecretary().getKey()).get(this).size() + "/" + sessionType.getCapacity();
    }

}
