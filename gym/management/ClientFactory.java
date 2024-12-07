package gym.management;
import gym.Gym;
import gym.customers.Client;
import gym.customers.Person;
import gym.Exception.InvalidAgeException;
import gym.Exception.DuplicateClientException;


public class ClientFactory {

    public static Client createClient(Person person) {
        if (!person.isAboveEightTeen()) {
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        }
        if (ClientRegistry.getInstance().isClientRegistered(person , Gym.getInstance().getSecretary().getKey())) {
            throw new DuplicateClientException("Error: The client is already registered");
        }

        return new Client(person);
    }
}
