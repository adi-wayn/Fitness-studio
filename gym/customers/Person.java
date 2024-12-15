package gym.customers;
import gym.management.Gym;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Person {
    private static int idCounter = 1111;
    private  int id;
    private final String name;
    private int balance;
    private final Gender gender;
    private final String birthDate;

    public Person(String name , int balance, Gender gender , String birthDate){
        this.id = idCounter;
        idCounter++;
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public int getId(){
        return this.id;
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
        return 2024 - Integer.parseInt(this.birthDate.substring(6 , 10));
    }

    public void subtractFromBalance(int amount) {

            this.balance -= amount;

    }

    public void addToBalance(int amount, String secretaryKey) throws SecurityException{
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

    public boolean isSenior() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(this.birthDate, formatter);

        LocalDate currentDate = LocalDate.now();
        int age = currentDate.getYear() - birthDate.getYear();
        if (currentDate.getMonthValue() < birthDate.getMonthValue() ||
                (currentDate.getMonthValue() == birthDate.getMonthValue() &&
                        currentDate.getDayOfMonth() < birthDate.getDayOfMonth())) {
            age--;
        }

        return age >= 65;
    }

}
