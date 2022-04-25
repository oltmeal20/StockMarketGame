package stockProjectBeta;


//main tester class from backend code
public class Main {

		public static void main(String[] args)
		{
			StockMarket market = new StockMarket();
			
			Broker user1 = new StockInvestor("Jim");
			
			market.addBroker(user1);
			
			market.addStock("AAPL", "Apple Inc.");
			
			market.preLoadStock();
			
			market.updateStock("aapl", 12.24);
			
			
		}
}
