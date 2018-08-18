public class Order implements LimitPriceOrders {
    private int id = 0;
    private String name;
    private int price;
    private int quantity;
    private Type currentType;
    private String timeCreation;

    public Order(String name, Type currentType, int price, int quantity, String timeCreation, int id) {
        this.name = name;
        this.currentType = currentType;
        this.price = price;
        this.quantity = quantity;
        this.timeCreation = timeCreation;
        this.id = id;
    }

    public String orderInfo(){
        return id + " " + name + " " + currentType + " " + price + " " + "@" + " " + quantity;}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Type getCurrentType() {
        return currentType;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + currentType + " " + price + " " + "@" + " " + quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTimeCreation() {
        return timeCreation;
    }
}
