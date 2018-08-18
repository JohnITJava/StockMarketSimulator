public interface TradingGateway {

   void addOrder(Order order);
   void cancelOrder();
   void getExecutions();
}
