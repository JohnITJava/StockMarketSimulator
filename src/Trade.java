import com.sun.org.apache.xpath.internal.operations.Or;

public class Trade implements TradingGateway{
    private OrderBooks orderBooks;

    public Trade(OrderBooks orderBooks){
        this.orderBooks = orderBooks;
    }

    @Override
    public void addOrder(Order order) {
        if (order.getCurrentType() == LimitPriceOrders.Type.Buy) {
            orderBooks.getBuyOrders().add(order);

        }
        if (order.getCurrentType() == LimitPriceOrders.Type.Sell){
            orderBooks.getSellOrders().add(order);
        }
    }

    @Override
    public void cancelOrder() {

    }

    @Override
    public void getExecutions() {

    }
}
