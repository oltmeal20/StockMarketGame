package stockProjectBeta;

import java.util.HashMap;
import java.util.List;

public class StockMarket extends AbstractStockMarket{	
	HashMap<String, Stock> stockList = new HashMap<String, Stock>();

	@Override
	public void addStock(String stockSymbol, String compName) {
		// TODO Auto-generated method stub
		Stock newStock = new Stock(stockSymbol, compName);
		stockList.put(stockSymbol, newStock);		
		
	}

	@Override
	public void updateStock(String stockSymbol, double price) {
		// TODO Auto-generated method stub
		Stock pointerStock = stockList.get(stockSymbol);
		pointerStock.setStockPrice(price);
		notifyBroker(stockList);
	}
	
	public void preLoadStock() {
		String[] stockSymbol = {"AAPL", "BAC", "MSFT", "AMZN", "FB", "WMT",};
		String[] compNames = {"Apple Inc.", "Bank of America", "Microsoft Corporation", "Amazon", "Facebook Inc.", "Wal-Mart Stores Inc."};
		for(int counter = 0; counter < compNames.length; counter++)
		{
			Stock getStock = new Stock(stockSymbol[counter], compNames[counter]);
			stockList.put(compNames[counter], getStock);
			System.out.println(compNames[counter] + " has been added.");
		}
	}
	
	public void sellStock(StockInvestor investor, String stockName, int quantity)
	{
		Stock tempStock = stockList.get(stockName);	
		System.out.println(investor.getName() + " bought: " + quantity + " at " + tempStock.getStockPrice() + " each.");
		investor.addStock(stockName, quantity);
	}
	
	public void printStock()
	{
		
			System.out.println(stockList);
		
	}
	
	public Stock getStockListIndex(String key)
	{
		return stockList.get(key);
		
	}
	
	public double getStockListPrice(String key)
	{
		Stock stock = stockList.get(key);
		return stock.getStockPrice();
	}

}
