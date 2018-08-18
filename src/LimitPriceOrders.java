public interface LimitPriceOrders {
    enum Type{
        Buy('B'), Sell('S');
        private char orderType;
        Type(char orderType) {this.orderType = orderType;}
    }
}
