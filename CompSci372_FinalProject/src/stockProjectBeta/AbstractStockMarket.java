package stockProjectBeta;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

abstract class AbstractStockMarket {
	
	private List<Broker> brokerList = new ArrayList<>();
	
	public void notifyBroker(Map<String, Stock> stockList)
	{
		for(Broker users: brokerList)
			users.update(stockList);
	}
	
	public void addBroker(Broker b)	
	{
		brokerList.add(b);
	}
	
	public abstract void addStock(String stockSymbol, String compName);
	
	public abstract void updateStock(String stockSybmol, double price);
	
	public abstract void sellStock(StockInvestor investor, String stockSymbol, int quantity);
}
