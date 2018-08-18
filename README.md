# StockMarketSimulator
Stock market simulator with the following components:
Matching engine, Order books, Trade ledger, Trading gateway (real exchanges can have multiple trading gateways)

Clients of the stock market will interact with it using Java API. These are the components:

-	Matching engine	"Balances" (like in "balancing the book", i.e. crossing buys and sells) the order books every 1 second
-	Order book(s) Contains all orders (buy and sell) for a certain stock
-	Trade ledger	Contains all trades that happen on all books
-	Trading gateway Allows a client to trade on the market. Exposes these functions:	add order, (optional) cancel order, (optional) get executions of an order (i.e. some of the volume for an order was executed)
The orders arriving at the trading gateway need to be put into the correct order book
