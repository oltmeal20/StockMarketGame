package stockProjectBeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class StockInvestor implements Broker{
	private final double INITAL_value = 10000; //for calculating point and percent values.
	private double buyingPower = 10000.00;
	private String name;
	private double  portfolioValue = buyingPower;
	private Hashtable<String, Integer> stockInvestments = new Hashtable<String, Integer>();
	
	public StockInvestor(String name)
	{
		this.setName(name);
	}
	@Override
	public void update(Map<String, Stock> stockList) {
		// TODO Auto-generated method stub
		System.out.println("StockInvestor: Price has changed");
		stockList.forEach((stockSymbol, stock) -> System.out.println(stockSymbol + " - $ " + stock.getStockPrice()));
	}

	@Override
	public void setName(String brokerName) {
		// TODO Auto-generated method stub
		this.name = brokerName;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	@Override
	public void addStock(String stockSymbol, int quantity)
	{
		if(stockInvestments.containsKey(stockSymbol))
		{
			int totalQty = stockInvestments.get(stockSymbol) + quantity;
			stockInvestments.replace(stockSymbol, totalQty);
		}
		else
			stockInvestments.put(stockSymbol, quantity);
			System.out.println("addStock called and added " + quantity + " " + stockSymbol + " to stockInvestments hashtable");
	}

	@Override
	public void decreaseStockQty(String stock) {
		// TODO Auto-generated method stub
		
		stockInvestments.get(stock);
		
	}
	
	public void sellStock(String stockName, int qtySold, double pricePerStock) {
		
		int newQty = stockInvestments.get(stockName) - qtySold;
		double totalValue = qtySold * pricePerStock;
		if(newQty == 0)
		{			
			setBuyingPower("sell", totalValue);
			stockInvestments.remove(stockName);
			System.out.println(stockName + ": was sold and removed");
		}
		else
		{
			setBuyingPower("sell", totalValue);
			stockInvestments.replace(stockName, newQty);
			System.out.printf("%d %s stocks were sold\n", qtySold, stockName);
		}
		
		
	}
	
	public String getStockInvestmentQty(String stockName) {
		int quantity = -1;
		if(stockName == null)
		{
			return "0";
		}
		else if(stockInvestments.get(stockName) == null)	
		{
			System.err.println(stockName + ": user has 0 Qty");
			return "0";
		}	
		else
			quantity = stockInvestments.get(stockName);			
			return Integer.toString(quantity);
	}
	
	public void setBuyingPower(String transaction, double value) {
		if(transaction == "buy")
			this.buyingPower = buyingPower - value;
		else if(transaction == "sell")
			this.buyingPower = buyingPower + value;
		else
			System.err.println("setBuyingPower went wrong");
		
	}
	public double getBuyingPower() {
		return buyingPower;
	}
	
	public void setPortfolioValue(double value) {
		this.portfolioValue = value;
	}
	public double getPortfolioValue() {
		return portfolioValue;
	}
	
	public String portfolioPercentValue() {
		
		DecimalFormat df = new DecimalFormat("0.00");
		double percentValue = (portfolioValue - INITAL_value) / INITAL_value;
		
		if(percentValue < 0)
			return df.format(percentValue) + "%";
		else 
			return "+ " + df.format(percentValue) + "%";

	}
	public String portfolioPointValue() {
		
		DecimalFormat df = new DecimalFormat("0.00");
		double pointValue = portfolioValue - INITAL_value;
		
		if (pointValue < 0)
			return df.format(pointValue);
		else
			return "+ " + df.format(pointValue);

	}
}
