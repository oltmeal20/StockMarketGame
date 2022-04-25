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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import stockProjectBeta.*;

public class StockController implements Initializable {
	
    /*** STOCK ***/
    
    @FXML private Label titleLabel;
    @FXML private CategoryAxis x;
    @FXML private NumberAxis y;
    @FXML private Button backButton;
    /** Apple Inc. **/
    @FXML private Label appleLabel;
    @FXML private LineChart<?, ?> appleLineChart;
    @FXML private AnchorPane appleScrollPane;
    @FXML private ListView<String> appleListView;
    /** Bank of America **/
    @FXML private Label bankOfAmericaLabel;
    @FXML private LineChart<?, ?> bankOfAmericaLineChart;
    @FXML private AnchorPane bankOfAmericaScrollPane;
    @FXML private ListView<String> bankOfAmericaListView;
    /** Microsoft Corporation **/
    @FXML private Label microsoftCorporationLabel;
    @FXML private LineChart<?, ?> microsoftCorporationLineChart;
    @FXML private AnchorPane microsoftCorporationScrollPane;
    @FXML private ListView<String> microsoftCorporationListView;
    /** Amazon **/
    @FXML private Label amozonLabel;
    @FXML private LineChart<?, ?> amazonLineChart;
    @FXML private AnchorPane amazonScrollPane;
    @FXML private ListView<String> amazonListView;
    /** Facebook Inc. **/
    @FXML private Label facebookLabel;
    @FXML private LineChart<?, ?> facebookLineChart;
    @FXML private AnchorPane facebookScrollPane;
    @FXML private ListView<String> facebookListView;
    /** Wal-Mart Stores Inc. **/
    @FXML private Label walmartStoresLabel;
    @FXML private LineChart<?, ?> walmartStoresLineChart;
    @FXML private AnchorPane walmartScrollPane;
    @FXML private ListView<String> walmartStoresListView;
    
    
    // Button Commands
    @FXML
    void backButtonPressed(ActionEvent event) throws IOException {
    	
    	switchScenes(event, "Personal.fxml");
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		StockMarket market = Opener.stockMarket;
		
		/*** Scroll Panes ***/
		
		// appleListView
		List<String> appleList = new ArrayList<String>();
		appleList.add("Current Value: " + market.getStockListPrice("Apple Inc."));
		appleList.add("52-week high:  ");
		appleList.add("52-week low:   ");	
		ObservableList<String> appleObList = FXCollections.observableList(appleList);
		appleListView.getItems().clear();
		appleListView.setItems(appleObList);

		// bankOfAmericaListView
		List<String> bankOfAmericaList = new ArrayList<String>();
		bankOfAmericaList.add("Current Value: " + market.getStockListPrice("Bank of America"));
		bankOfAmericaList.add("52-week high:  ");
		bankOfAmericaList.add("52-week low:   ");		
		ObservableList<String> bankOfAmericaObList = FXCollections.observableList(bankOfAmericaList);
		bankOfAmericaListView.getItems().clear();
		bankOfAmericaListView.setItems(bankOfAmericaObList);

		// microsoftCorporationListView
		List<String> microsoftCorporationList = new ArrayList<String>();
		microsoftCorporationList.add("Current Value: " + market.getStockListPrice("Microsoft Corporation"));
		microsoftCorporationList.add("52-week high:  ");
		microsoftCorporationList.add("52-week low:   ");	
		ObservableList<String> microsoftCorporationObList = FXCollections.observableList(microsoftCorporationList);
		microsoftCorporationListView.getItems().clear();
		microsoftCorporationListView.setItems(microsoftCorporationObList);

		// amazonListView
		List<String> amazonList = new ArrayList<String>();
		amazonList.add("Current Value: " + market.getStockListPrice("Amazon"));
		amazonList.add("52-week high:  ");
		amazonList.add("52-week low:   ");		
		ObservableList<String> amazonObList = FXCollections.observableList(amazonList);
		amazonListView.getItems().clear();
		amazonListView.setItems(amazonObList);

		// facebookListView
		List<String> facebookList = new ArrayList<String>();
		facebookList.add("Current Value: " + market.getStockListPrice("Facebook Inc."));
		facebookList.add("52-week high:  ");
		facebookList.add("52-week low:   ");		
		ObservableList<String> facebookObList = FXCollections.observableList(facebookList);
		facebookListView.getItems().clear();
		facebookListView.setItems(facebookObList);

		// walmartStoresListView
		List<String> walmartStoresList = new ArrayList<String>();
		walmartStoresList.add("Current Value: " + market.getStockListPrice("Wal-Mart Stores Inc."));
		walmartStoresList.add("52-week high:  ");
		walmartStoresList.add("52-week low:   ");		
		ObservableList<String> walmartStoresObList = FXCollections.observableList(walmartStoresList);
		walmartStoresListView.getItems().clear();
		walmartStoresListView.setItems(walmartStoresObList);
        
        
        /*** Line Charts ***/

		// appleLineChart
		XYChart.Series series1 = new XYChart.Series<>();
        series1.setName("Series 1");
        series1.getData().add(new XYChart.Data("1", 20));
        series1.getData().add(new XYChart.Data("2", 10));
        series1.getData().add(new XYChart.Data("3", 80));
        series1.getData().add(new XYChart.Data("4", 110));
        series1.getData().add(new XYChart.Data("5", 5));
        series1.getData().add(new XYChart.Data("6", 100));
        appleLineChart.getData().addAll(series1);
        
        //bankOfAmericaLineChart
        XYChart.Series series2 = new XYChart.Series<>();
        series2.setName("series2");
        series2.getData().add(new XYChart.Data("1", 10));
        series2.getData().add(new XYChart.Data("2", 40));
        series2.getData().add(new XYChart.Data("3", 50));
        series2.getData().add(new XYChart.Data("4", 10));
        series2.getData().add(new XYChart.Data("5", 100));
        series2.getData().add(new XYChart.Data("6", 80));
        bankOfAmericaLineChart.getData().addAll(series2);
        
        //microsoftCorporationLineChart
        XYChart.Series series3 = new XYChart.Series<>();
        series3.setName("series3");
        series3.getData().add(new XYChart.Data("1", 60));
        series3.getData().add(new XYChart.Data("2", 20));
        series3.getData().add(new XYChart.Data("3", 40));
        series3.getData().add(new XYChart.Data("4", 90));
        series3.getData().add(new XYChart.Data("5", 80));
        series3.getData().add(new XYChart.Data("6", 10));
        microsoftCorporationLineChart.getData().addAll(series3);
        
        //amazonLineChart
        XYChart.Series series4 = new XYChart.Series<>();
        series4.setName("series4");
        series4.getData().add(new XYChart.Data("1", 50));
        series4.getData().add(new XYChart.Data("2", 50));
        series4.getData().add(new XYChart.Data("3", 40));
        series4.getData().add(new XYChart.Data("4", 20));
        series4.getData().add(new XYChart.Data("5", 70));
        series4.getData().add(new XYChart.Data("6", 10));
        amazonLineChart.getData().addAll(series4);
     
        //facebookLineChart
        XYChart.Series series5 = new XYChart.Series<>();
        series5.setName("series5");
        series5.getData().add(new XYChart.Data("1", 100));
        series5.getData().add(new XYChart.Data("2", 20));
        series5.getData().add(new XYChart.Data("3", 30));
        series5.getData().add(new XYChart.Data("4", 60));
        series5.getData().add(new XYChart.Data("5", 80));
        series5.getData().add(new XYChart.Data("6", 60));
        facebookLineChart.getData().addAll(series5);
        
        //walmartStoresLineChart
        XYChart.Series series6 = new XYChart.Series<>();
        series6.setName("series6");
        series6.getData().add(new XYChart.Data("1", 30));
        series6.getData().add(new XYChart.Data("2", 10));
        series6.getData().add(new XYChart.Data("3", 20));
        series6.getData().add(new XYChart.Data("4", 70));
        series6.getData().add(new XYChart.Data("5", 70));
        series6.getData().add(new XYChart.Data("6", 100));
        walmartStoresLineChart.getData().addAll(series6);

	}

}
