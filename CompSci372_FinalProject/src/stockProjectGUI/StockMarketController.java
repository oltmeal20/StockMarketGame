package stockProjectGUI;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class StockMarketController{

	
	
	/*** OPENER ***/
	
	@FXML
    private Label openerTitleLabel;
	
    @FXML
    private Button playButton;

    @FXML
    private Button exitButton;

    // Button Commands
    @FXML
    void exitButtonPressed(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void playButtonPressed(ActionEvent event) throws IOException {
    	
    	switchScenes(event, "Personal.fxml");
    }
    
    
    
    /*** PERSONAL ***/
    
    @FXML
    private Label personalTitleLabel;

    @FXML
    private Label moneyLabel;

    @FXML
    private Label marketValueLabel;

    @FXML
    private Label marketValueEditableLabel;

    @FXML
    private Label buyingPowerLabel;

    @FXML
    private Label buyingPowerEditableLabel;

    @FXML
    private Label lastTradeLabel;

    @FXML
    private Label lastTradeEditableLabel;

    @FXML
    private ChoiceBox<String> stockChoiceBox;

    @FXML
    private Button minusButton;

    @FXML
    private TextField moneyTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button sellButton;

    @FXML
    private Button buyButton;

    @FXML
    private LineChart<?, ?> PersonalLineChart;

    @FXML
    private Label highLabel;

    @FXML
    private Label highEditableLabel;

    @FXML
    private Label lowLabel;

    @FXML
    private Label lowEditableLabel;

    @FXML
    private Label closeLabel;

    @FXML
    private Label closeEditableLabel;

    @FXML
    private Label adjCloseLabel;

    @FXML
    private Label adjCloseEditableLabel;

    @FXML
    private Label openLabel;

    @FXML
    private Label openEditableLabel;

    @FXML
    private Label volumeLabel;

    @FXML
    private Label volumeEditableLabel;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button stockButton;

    @FXML
    private Button nextDayButton;
     
    // Button Commands
    @FXML
    void addButtonPressed(ActionEvent event) {

    }

    @FXML
    void buyButtonPressed(ActionEvent event) {

    }

    @FXML
    void mainMenuButtonPressed(ActionEvent event) throws IOException {
    	
    	switchScenes(event, "Opener.fxml");
    }

    @FXML
    void minusButtonPressed(ActionEvent event) {

    }

    @FXML
    void nextDayButtonPressed(ActionEvent event) {

    }

    @FXML
    void sellButtonPressed(ActionEvent event) {

    }

    @FXML
    void stockButtonPressed(ActionEvent event) throws IOException {
    	
    	switchScenes(event, "Stock.fxml");
    }
    
    
    
    /*** STOCK ***/
    
    @FXML
    private Label titleLabel;

    @FXML
    private Label appleLabel;

    @FXML
    private LineChart<?, ?> appleLineChart;

    @FXML
    private AnchorPane appleScrollPane;

    @FXML
    private Label bankOfAmericaLabel;

    @FXML
    private LineChart<?, ?> bankOfAmericaLineChart;

    @FXML
    private AnchorPane bankOfAmericaScrollPane;

    @FXML
    private Label microsoftCorporationLabel;

    @FXML
    private LineChart<?, ?> microsoftCorporationLineChart;

    @FXML
    private AnchorPane microsoftCorporationScrollPane;

    @FXML
    private Label amozoneLabel;

    @FXML
    private LineChart<?, ?> amazoneLineChart;

    @FXML
    private AnchorPane amazoneScrollPane;

    @FXML
    private Label facebookLabel;

    @FXML
    private LineChart<?, ?> facebookLineChart;

    @FXML
    private AnchorPane facebookScrollPane;

    @FXML
    private Label walmartStoresLabel;

    @FXML
    private LineChart<?, ?> walmartStoresLineChart;

    @FXML
    private AnchorPane walmartScrollPane;

    @FXML
    private Button backButton;
    
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
    
    public void getTickerPrice()
    {
    	moneyTextField.setText("100.00");
    }

}
