package gym.management.Sessions;
import gym.Gym;
import gym.management.Instructor;
import gym.management.RegisterClientToSession;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Session {
    private final SessionType sessionType;
    private final String date;
    private final ForumType forumType;
    private final Instructor instructor;

    public Session(SessionType sessionType, String date, ForumType forumType,Instructor instructor) {
        this.sessionType = sessionType;
        this.date = date;
        this.forumType = forumType;
        this.instructor = instructor;
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
