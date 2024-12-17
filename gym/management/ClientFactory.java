package gym.management;
import gym.customers.Client;
import gym.customers.Person;
import gym.Exception.InvalidAgeException;
import gym.Exception.DuplicateClientException;


class ClientFactory {

    public static Client createClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if (!person.isAboveEightTeen()) {
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        }
        if (Gym.getInstance().getSecretary().getClientRegistry().getRegistered(Gym.getInstance().getSecretary().getKey()).stream().anyMatch(client -> client.getPerson().equals(person))) {
            throw new DuplicateClientException("Error: The client is already registered");
        }

        return new Client(person);
    }
}