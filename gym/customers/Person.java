package gym.customers;
import gym.Gym;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Person {
    private final String name;
    private int balance;
    private final Gender gender;
    private final String birthDate;

    public Person(String name , int balance, Gender gender , String birthDate){
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public Person(Person other) {
        this.name = other.getName();
        this.balance = other.getBalance();
        this.gender = other.getGender();
        this.birthDate = other.getBirthDate();
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public int getAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(this.birthDate, formatter);
        LocalDate currentDate = LocalDate.now();

        return Period.between(birthDate, currentDate).getYears();
    }

    public void subtractFromBalance(int amount) {

            this.balance -= amount;

    }

    public void addToBalance(int amount, String secretaryKey){
        String key = Gym.getInstance().getSecretary().getKey();
        if (!secretaryKey.equals(key))
            throw new SecurityException("Wrong Key,access denied");

        this.balance += amount;

    }

    public String getBirthDate() {
        return birthDate;
    }

    public boolean isAboveEightTeen() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthdate = LocalDate.parse(this.birthDate, formatter);
        LocalDate today = LocalDate.now();
        LocalDate eighteenthBirthday = birthdate.plusYears(18);

        return !today.isBefore(eighteenthBirthday);

    }

    public boolean isSenior(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currYear = currentDateTime.getYear();
        int currMonth = currentDateTime.getMonthValue();
        int currDay = currentDateTime.getDayOfMonth();

        int year = Integer.parseInt(this.birthDate.substring(6,9));
        int month = Integer.parseInt(this.birthDate.substring(3, 4));
        int day = Integer.parseInt(this.birthDate.substring(0, 1));

        int yearRemainder = currYear - year;
        int monthRemainder = currMonth - month;
        int dayRemainder = currDay - day;

        if (yearRemainder > 65)
            return true;

        else return yearRemainder == 65  && monthRemainder >= 0 && dayRemainder >= 0;
    }
}
