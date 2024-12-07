package gym.management;
import gym.Gym;
import gym.customers.*;

import java.util.*;

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

    public void addClient(Client client, String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        clients.add(client);
    }

    public void removeClient(Client client, String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        clients.remove(client);
    }

    public boolean isClientRegistered(Person person, String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        return clients.stream().anyMatch(client -> client.getPerson().equals(person));
    }

    public boolean isClientRegistered(Client client , String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        return clients.contains(client);
    }

    public List<Client> getAllClients(String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        return new ArrayList<>(clients);
    }

    @Override
    public String toString() {
        StringBuilder clientsData = new StringBuilder();

        List<Client> sortedList =  getAllClients(Gym.getInstance().getSecretary().getKey());
        sortedList.sort(Comparator.comparingInt(Client::getId));

        for (Client client : sortedList)
            clientsData.append(client).append("\n");

        return clientsData.toString();
    }
}
