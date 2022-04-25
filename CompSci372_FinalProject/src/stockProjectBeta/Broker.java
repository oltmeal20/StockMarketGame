package stockProjectBeta;

import java.util.HashMap;
import java.util.Map;

interface Broker {
	
	
		void update(Map<String, Stock> stockList);		
		void setName(String brokerName);		
		String getName();	
		void decreaseStockQty(String stock);
		void addStock(String stockSymbol, int quantity);
}
