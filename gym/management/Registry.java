package gym.management;
import java.util.*;

class Registry <T extends Registrable> {
    private final Set<T> registered;

    public Registry() {
        this.registered = new HashSet<>();
    }

    public Set<T> getRegistered(String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        return this.registered;
    }

    public void add(T data , String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        this.registered.add(data);
    }

    public void remove(T data , String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        this.registered.remove(data);
    }

    public boolean isRegistered(T data , String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        return this.registered.contains(data);
    }

    public List<T> getAll(String key) throws SecurityException {
        if (!Gym.getInstance().getSecretary().getKey().equals(key))
            throw new SecurityException("Unmatched key.\nAccess denied.");

        return new ArrayList<>(this.registered);
    }

    @Override
    public String toString() {
        StringBuilder Data = new StringBuilder();

        List<T> sortedList =  getAll(Gym.getInstance().getSecretary().getKey());
        sortedList.sort(Comparator.comparingInt(T::getId));

        for (int i = 0; i < sortedList.size(); i++) {
            if (i != sortedList.size()-1)
                Data.append(sortedList.get(i)).append("\n");
            else
                Data.append(sortedList.get(i));
        }

        return Data.toString();
    }
}
