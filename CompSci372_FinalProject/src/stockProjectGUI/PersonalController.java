package stockProjectGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;

import stockProjectBeta.*;

public class PersonalController implements Initializable {
	static StockInvestor user = new StockInvestor("Foo");
	StockMarket market = Opener.stockMarket;
	private int stockQuantity = 0;
	
    /*** PERSONAL ***/
    @FXML private Label personalTitleLabel;
    /** HBox (0,0) **/ 
    @FXML private Label moneyLabel;
    @FXML private Label pointGainLabel;
    @FXML private Label percentGainLabel;
    /** HBox (0,2), GridPane **/
    @FXML private Label marketValueLabel;
    @FXML private Label marketValueEditableLabel;
    @FXML private Label buyingPowerLabel;
    @FXML private Label buyingPowerEditableLabel;
    @FXML private Label lastTradeLabel;
    @FXML private Label lastTradeEditableLabel;
    /** choiceBox w/HBox **/
    @FXML private ComboBox<String> stockComboBox;
    @FXML private Button minusButton;
    @FXML private TextField quantityTextField;
    @FXML private TextField moneyTextField;
    @FXML private Button addButton;
    @FXML private Button sellButton;
    @FXML private Button buyButton;
    /** LineChart **/
    @FXML private LineChart<String, Integer> PersonalLineChart;
    @FXML private CategoryAxis x;
    @FXML private NumberAxis y;
    /** GridPane bottom **/
    @FXML private Label ownedAmazonLabel;
    @FXML private Label ownedAppleLabel;
    @FXML private Label ownedBoaLabel;
    @FXML private Label ownedFacebookLabel;
    @FXML private Label ownedMicrosoftLabel;
    @FXML private Label ownedWalMartLabel;
    /** HBox Bottom **/
    @FXML private Button mainMenuButton;
    @FXML private Button stockButton;
    @FXML private Button nextDayButton;
     
    // Button Commands
    @FXML
    void mainMenuButtonPressed(ActionEvent event) throws IOException {
    	
    	switchScenes(event, "Opener.fxml");
    }
    
    @FXML
    void addButtonPressed(ActionEvent event) {
    	if(stockQuantity <= 0)
    	{
    		System.out.println("stockQuantity equals: " + stockQuantity + " no value can be added.");
    		return;
    	}
    	else
    		stockQuantity++;
    		Stock tempStock = market.getStockListIndex(stockComboBox.getValue()); 
    		double tempStockValue = stockQuantity * tempStock.getStockPrice();
    		String tempStockPrice = String.format("%.2f", tempStockValue);
    		moneyTextField.setText("$ " + tempStockPrice);
    		quantityTextField.setText(Integer.toString(stockQuantity));
    		System.out.printf("Adding... contains %d %s total: %s\n", stockQuantity, tempStock.getCompanyName(), tempStockPrice);
    }
    
    @FXML
    void minusButtonPressed(ActionEvent event) {
    	if(stockQuantity <= 1) {
    		System.out.println("stockQuantity equals: " + stockQuantity + " and cannot go below 1");
    		return;
    	}
    	else
    	stockQuantity--;
    	Stock tempStock = market.getStockListIndex(stockComboBox.getValue()); 
		double tempStockValue = stockQuantity * tempStock.getStockPrice();
		String tempStockPrice = String.format("%.2f", tempStockValue);
    	moneyTextField.setText("$ " + tempStockPrice);
    	quantityTextField.setText(Integer.toString(stockQuantity));
    	System.out.printf("Adding... contains %d %s total: %s\n", stockQuantity, tempStock.getCompanyName(), tempStockPrice);
    }
    
    @FXML
    void buyButtonPressed(ActionEvent event) {
    	if(moneyTextField.getText().isEmpty())
    	{
    		System.out.println("cannot buy, empty stocklist.");
    		return;
    		
    	}
    	else
    	{
    		String rawValue = moneyTextField.getText(1,moneyTextField.getLength());
    		double totalValue = Double.valueOf(rawValue);
    		System.out.println("Buy button was pressed, value " + totalValue);
    		if(totalValue > user.getBuyingPower())
    		{
    			System.out.println("cannot buy, user only has: " + user.getBuyingPower());
    			return;
    		}
    		else
    		{
    			user.setBuyingPower("buy", totalValue);
    			market.sellStock(user, stockComboBox.getValue(), stockQuantity);
    			buyingPowerEditableLabel.setText(String.format("%.2f", user.getBuyingPower()));
    			updateOwnedStock(stockComboBox.getValue());
    		}
    	}
   }

    @FXML
    void sellButtonPressed(ActionEvent event) {
    	String compNameQty = user.getStockInvestmentQty(stockComboBox.getValue());
    	String compName = stockComboBox.getValue();
    	
    	if(compNameQty == "0" || compNameQty == " ")
    	{
    		System.out.println("cannot sell stock you dont have. Or null list was found!");
    		return;    		
    	}
    	int requestedQty = Integer.parseInt(quantityTextField.getText());
    	if(requestedQty > getOwnedStock(compName))
    	{
    		System.out.println("trying to sell more than owned");
    		return;
    		
    	}
    	else {
    		Stock pointerStock = market.getStockListIndex(compName);
    		user.sellStock(compName, requestedQty, pointerStock.getStockPrice());
    		buyingPowerEditableLabel.setText(String.format("%.2f", user.getBuyingPower()));
    		updateOwnedStock(stockComboBox.getValue());
    		}
    		
    		
    		
    }

    @FXML
    void nextDayButtonPressed(ActionEvent event) {

    }


    
    @FXML
    void stockComboBoxOnAction(ActionEvent event) {
    	stockQuantity = 1;
    	System.out.println("stockComboBox display: " + stockComboBox.getValue());
    	Stock tempStock = market.getStockListIndex(stockComboBox.getValue()); 
    	String tempStockPrice = Double.toString(stockQuantity * tempStock.getStockPrice());
    	moneyTextField.setText("$ " + tempStockPrice);
    	quantityTextField.setText(Integer.toString(stockQuantity));
    }
    @FXML
    void stockButtonPressed(ActionEvent event) throws IOException {
    	
    	switchScenes(event, "Stock.fxml");
    }
    
    /*** METHODS ***/
    
    
    // method allows application to switch scenes with press of a button
    public void switchScenes(ActionEvent event, String sceneName) throws IOException{
    	 
    	Parent parent = FXMLLoader.load(getClass().getResource(sceneName));
    	Scene scene = new Scene(parent);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(scene);
    	window.show();
    }
    
    public void updatePortfolioValue() {
        moneyLabel.setText("$ " + Double.toString(user.getPortfolioValue()));
        pointGainLabel.setText(user.portfolioPointValue());
        percentGainLabel.setText(user.portfolioPercentValue());
    }
    
    public int getOwnedStock(String stockName) {
    	switch(stockName) {
    	case "Amazon":
    			return Integer.parseInt(ownedAmazonLabel.getText());  			
    	case "Apple Inc.":
    			return Integer.parseInt(ownedAppleLabel.getText());
    	case "Bank of America":
    			return Integer.parseInt(ownedBoaLabel.getText());
    	case "Facebook Inc.":
    			return Integer.parseInt(ownedFacebookLabel.getText());
    	case "Microsoft Corporation":
    			return Integer.parseInt(ownedMicrosoftLabel.getText());
    	case "Wal-Mart Stores Inc.":
    			return Integer.parseInt(ownedWalMartLabel.getText());
    	}
		return -1;
    }
    
    public void updateOwnedStock(String stockName) {
    	switch(stockName) {
    	case "Amazon":
    			ownedAmazonLabel.setText(user.getStockInvestmentQty("Amazon"));
    			break;
    	case "Apple Inc.":
    			ownedAppleLabel.setText(user.getStockInvestmentQty("Apple Inc."));
    			break;
    	case "Bank of America":
    			ownedBoaLabel.setText(user.getStockInvestmentQty("Bank of America"));
    			break;
    	case "Facebook Inc.":
    			ownedFacebookLabel.setText(user.getStockInvestmentQty("Facebook Inc."));
    	  		break;
    	case "Microsoft Corporation":
    			ownedMicrosoftLabel.setText(user.getStockInvestmentQty("Microsoft Corporation"));
    	  		break;
    	case "Wal-Mart Stores Inc.":
    			ownedWalMartLabel.setText(user.getStockInvestmentQty("Wal-Mart Stores Inc."));
    			break;
    			
    	}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		updatePortfolioValue();
		buyingPowerEditableLabel.setText(String.format("%.2f", user.getBuyingPower()));
		
		/*** Choice Box ***/
		
		List<String> list = new ArrayList<String>();
        list.add("Apple Inc.");
        list.add("Bank of America");
        list.add("Microsoft Corporation");
        list.add("Amazon");
        list.add("Facebook Inc.");
        list.add("Wal-Mart Stores Inc.");
        ObservableList<String> obList = FXCollections.observableList(list);
        stockComboBox.getItems().addAll(obList);
        
        /*** Text Box ***/
        


        
        
        
        /*** Line Chart ***/
        
        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
        series1.setName("Series 1");
        series1.getData().add(new XYChart.Data<String, Integer>("1", 20));
        series1.getData().add(new XYChart.Data<String, Integer>("2", 10));
        series1.getData().add(new XYChart.Data<String, Integer>("3", 80));
        series1.getData().add(new XYChart.Data<String, Integer>("4", 110));
        series1.getData().add(new XYChart.Data<String, Integer>("5", 5));
        series1.getData().add(new XYChart.Data<String, Integer>("6", 100));
        
        XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
        series2.setName("series2");
        series2.getData().add(new XYChart.Data<String, Integer>("1", 10));
        series2.getData().add(new XYChart.Data<String, Integer>("2", 40));
        series2.getData().add(new XYChart.Data<String, Integer>("3", 50));
        series2.getData().add(new XYChart.Data<String, Integer>("4", 10));
        series2.getData().add(new XYChart.Data<String, Integer>("5", 100));
        series2.getData().add(new XYChart.Data<String, Integer>("6", 80));
        
        XYChart.Series<String, Integer> series3 = new XYChart.Series<>();
        series3.setName("series3");
        series3.getData().add(new XYChart.Data<String, Integer>("1", 60));
        series3.getData().add(new XYChart.Data<String, Integer>("2", 20));
        series3.getData().add(new XYChart.Data<String, Integer>("3", 40));
        series3.getData().add(new XYChart.Data<String, Integer>("4", 90));
        series3.getData().add(new XYChart.Data<String, Integer>("5", 80));
        series3.getData().add(new XYChart.Data<String, Integer>("6", 10));
        
        PersonalLineChart.getData().addAll(series1, series2, series3);
		
	}

}
