package stockProjectGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import stockProjectBeta.StockMarket;



public class OpenerController implements Initializable {
	
    /*** OPENER ***/
	
	@FXML private Label openerTitleLabel;
    @FXML private Button playButton;
    @FXML private Button exitButton;

    // Button Commands
    @FXML
    void exitButtonPressed(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void playButtonPressed(ActionEvent event) throws IOException {
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

		
	}

}
