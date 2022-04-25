package stockProjectBeta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.text.ParseException;

public class Stock {
		//General information variables
		private String companyName;
		private double stockPrice;
		private int stock_quantity;
		
		public Stock(String stockSymbol, String companyName)
		{
			this.setCompanyName(companyName);
			
			try {
				this.setStockPrice(startPrice(stockSymbol));
			} catch (IOException e) {
				System.err.printf("Error: %s start price could not be set.\n", stockSymbol);
			}
		}
		public Stock() {};
		//basic information about stock
		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public double getStockPrice() {
			return stockPrice;
		}

		public void setStockPrice(double stockPrice) {
			this.stockPrice = stockPrice;
		}
		
		public int getStock_quantity() {
			return stock_quantity;
		}
		public void setStock_quantity(int stock_quantity) {
			this.stock_quantity = stock_quantity;
		}
		

		
		//private method for getting a starting price for a newly added stock.
		private double startPrice (String stockSymbol) throws IOException
		{
			NumberFormat format = NumberFormat.getCurrencyInstance();
			URL url = new URL("https://finance.yahoo.com/quote/" +stockSymbol+ "?p=" +stockSymbol+ "&.tsrc=fin-srch");
			URLConnection urlConn =  url.openConnection();
			InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
			BufferedReader buff = new BufferedReader(inStream);
			
			String line = buff.readLine();
			String price = "-1";
			while(line != null)
			{
				if(line.contains("<div class=\"My(6px) Pos(r) smartphone_Mt(6px)\""))
				{
				int target = line.indexOf("class=\"Trsdu(0.3s) Trsdu(0.3s) Fw(b) Fz(36px) Mb(-4px) D(b)\" ");
				int targetGreatSign = line.indexOf(">", target);
				int start = targetGreatSign;
				int end = start;
				while(line.charAt(end) != '<')
				{
					end++;				
				}
				
				price = line.substring(start + 1, end);

				
				line = null;
				}
				else
				line = buff.readLine();
			}
			price = price.replaceAll(",", "");
			double parsePrice = Double.parseDouble(price);
			return parsePrice;
		}

}
