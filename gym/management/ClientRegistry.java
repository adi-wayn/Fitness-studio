package gym.management;
import gym.customers.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientRegistry {
    private static ClientRegistry instance;
    private final Set<Client> clients;

    private ClientRegistry() {
        clients = new HashSet<>();
    }

    public static ClientRegistry getInstance() {
        if (instance == null) {
            instance = new ClientRegistry();
        }
        return instance;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public boolean isClientRegistered(Person person) {
        return clients.stream().anyMatch(client -> client.getPerson().equals(person));
    }

    public boolean isClientRegistered(Client client) {
        return clients.contains(client);
    }

    public List<Client> getAllClients() {
        return new ArrayList<>(clients);
    }

    @Override
    public String toString() {
        StringBuilder clientsData = new StringBuilder();

        for (Client client : getAllClients())
            clientsData.append(client).append("\n");

        return clientsData.toString();
    }
}
