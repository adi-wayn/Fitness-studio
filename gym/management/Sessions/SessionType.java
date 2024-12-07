package gym.management.Sessions;

public enum SessionType {
    ThaiBoxing( 100, 20),
    MachinePilates( 80,10),
    Pilates(60,30),
    Ninja(150 , 5);

    private int price;
    private int capacity;

    SessionType(int price , int capacity){
        this.price = price;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
