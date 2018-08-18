import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class MatchingEngine {

    public static void main(String[] args) throws IOException, InterruptedException {

        int id = 0;

        /*Order o = new Order("Goog", LimitPriceOrders.Type.Buy, 100, 50, "1234", ++id);
        Order o1 = new Order("Goog", LimitPriceOrders.Type.Buy, 50, 100, "1234", ++id);
        Order o2 = new Order("Goog", LimitPriceOrders.Type.Buy, 150, 20, "1234", ++id);
        Order o3 = new Order("Rambler", LimitPriceOrders.Type.Buy, 100, 20, "1234", ++id);
        Order o4 = new Order("Rambler", LimitPriceOrders.Type.Buy, 80, 50, "1234", ++id);

        Order o5 = new Order("Goog", LimitPriceOrders.Type.Sell, 100, 30, "1234", ++id);
        Order o6 = new Order("Goog", LimitPriceOrders.Type.Sell, 30, 100, "1234", ++id);
        Order o7 = new Order("Goog", LimitPriceOrders.Type.Sell, 200, 20, "1234", ++id);
        Order o8 = new Order("Rambler", LimitPriceOrders.Type.Sell, 100, 20, "1234", ++id);
        Order o9 = new Order("Rambler", LimitPriceOrders.Type.Sell, 60, 30, "1234", ++id);*/


        Date date = new Date();
        TradeLedger tradeLedger = new TradeLedger();
        OrderBooks orderBooks = new OrderBooks(tradeLedger);

        /*orderBooks.getBuyOrders().add(o);
        orderBooks.getBuyOrders().add(o1);
        orderBooks.getBuyOrders().add(o2);
        orderBooks.getBuyOrders().add(o3);
        orderBooks.getBuyOrders().add(o4);
        orderBooks.getSellOrders().add(o5);
        orderBooks.getSellOrders().add(o6);
        orderBooks.getSellOrders().add(o7);
        orderBooks.getSellOrders().add(o8);
        orderBooks.getSellOrders().add(o9);*/

        TradingGateway trade = new Trade(orderBooks);
        Order order;
        String inputCommand;

        int price;
        int quantity;
        LimitPriceOrders.Type type = null;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        new Thread(() -> {
            for (; ;) {
                try {
                    Thread.sleep(1000);
                    orderBooks.balancing();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while (true) {
            System.out.println("Enter your Order");
        inputCommand = input.readLine();
        String[] tokens = inputCommand.split("\\s");
        if (tokens[0].equals("add")) {
            if (tokens[2].equals("B")) type = Order.Type.Buy;
            if (tokens[2].equals("S")) type = Order.Type.Sell;
            price = Integer.parseInt(tokens[3]);
            quantity = Integer.parseInt(tokens[4]);
            order = new Order(tokens[1], type, price, quantity, date.toString(), ++id);
            //order.setId(++id);
            trade.addOrder(order);
            tradeLedger.addRecord("Order with ID " + order.getId() + " added: " + order.getName() + " " + order.getCurrentType() +
                    " " + order.getPrice() + " @ " + order.getQuantity());
        }}

    }
}
