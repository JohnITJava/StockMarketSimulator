import java.util.ArrayList;
import java.util.HashMap;

public class OrderBooks {
    private TradeLedger tradeLedger;

    private int deltatmpQuant = 0;
    

    private ArrayList<Order> buyOrders = new ArrayList<>();
    private ArrayList<Order> sellOrders = new ArrayList<>();

    public ArrayList<Order> getBuyOrders() {
        return buyOrders;
    }

    public ArrayList<Order> getSellOrders() {
        return sellOrders;
    }

    public void balancing() throws InterruptedException {
        for (int i = 0; i < this.buyOrders.size(); i++) {
            for (int j = 0; j < this.sellOrders.size(); j++) {
                if (buyOrders.get(i).getName().equals(sellOrders.get(j).getName())) {
                    if (buyOrders.get(i).getPrice() >= sellOrders.get(j).getPrice()) {
                        deltatmpQuant = buyOrders.get(i).getQuantity() - sellOrders.get(j).getQuantity();
                        if (deltatmpQuant == 0) {
                            tradeLedger.addRecord("New execution with ID " + buyOrders.get(i).getId() +
                                    ": " + buyOrders.get(i).getPrice() + " @ " + buyOrders.get(i).getQuantity() +
                                    " (orders " + buyOrders.get(i).getId() + " and " + sellOrders.get(j).getId() + " was full realized)");
                            tradeLedger.addRecord(buyOrders.get(i).orderInfo() + " was removed from BuyOrdersBook");
                            buyOrders.remove(i);
                            tradeLedger.addRecord(sellOrders.get(j).orderInfo() + " was removed from SellOrdersBook");
                            sellOrders.remove(j);
                        } else if (deltatmpQuant > 0) {
                            tradeLedger.addRecord("New execution with ID " + buyOrders.get(i).getId() +
                                    ": " + buyOrders.get(i).getPrice() + " @ " + buyOrders.get(i).getQuantity() +
                                    " (orders " + buyOrders.get(i).getId() + " and " + sellOrders.get(j).getId() + " partial realized)");
                            tradeLedger.addRecord(buyOrders.get(i).orderInfo() + " was changed quantity to " + deltatmpQuant + " (partial realized)");
                            buyOrders.get(i).setQuantity(deltatmpQuant);
                            tradeLedger.addRecord(sellOrders.get(j).orderInfo() + " was removed from SellOrdersBook");
                            sellOrders.remove(j);
                        } else if (deltatmpQuant < 0) {
                            tradeLedger.addRecord("New execution with ID " + buyOrders.get(i).getId() +
                                    ": " + buyOrders.get(i).getPrice() + " @ " + buyOrders.get(i).getQuantity() +
                                    " (orders " + buyOrders.get(i).getId() + " and " + sellOrders.get(j).getId() + " partial realized)");
                            tradeLedger.addRecord(sellOrders.get(j).orderInfo() + " was changed quantity to " + Math.abs(deltatmpQuant) + " (partial realized)");
                            sellOrders.get(j).setQuantity(Math.abs(deltatmpQuant));
                            tradeLedger.addRecord(buyOrders.get(i).orderInfo() + " was removed from BuyOrdersBook");
                            buyOrders.remove(i);
                        }
                    }
                }
            }
        }
    }

    /*public HashMap<String, Order> getSellOrders() {
        return sellOrders;
    }
    public HashMap<String, Order> getBuyOrders() {
        return buyOrders;
    }

    public OrderBooks(HashMap<String, Order> sellOrders) {
        this.sellOrders = sellOrders;
    }

    public boolean checkExistBuyOrder(Order order){
        return buyOrders.get(order.getName()) == null;
    }

    public boolean checkExistSellOrder(Order order){
        return buyOrders.get(order.getName()) == null;
    }*/

    public OrderBooks(TradeLedger tradeLedger) {
        this.tradeLedger = tradeLedger;
    }
}
