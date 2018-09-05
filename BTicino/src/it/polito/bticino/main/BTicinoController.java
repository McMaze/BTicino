package it.polito.bticino.main;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.bticino.lib.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;



public class BTicinoController {

	
	Model model; 
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnLuceAllOn"
    private Button btnLuceAllOn; // Value injected by FXMLLoader

    @FXML // fx:id="btnLuceAllOff"
    private Button btnLuceAllOff; // Value injected by FXMLLoader

    @FXML // fx:id="btnLuce1On"
    private Button btnLuce1On; // Value injected by FXMLLoader

    @FXML // fx:id="btnLuce1Off"
    private Button btnLuce1Off; // Value injected by FXMLLoader

    @FXML // fx:id="btnLuce2On"
    private Button btnLuce2On; // Value injected by FXMLLoader

    @FXML // fx:id="btnLuce2Off"
    private Button btnLuce2Off; // Value injected by FXMLLoader

    @FXML // fx:id="btnLuce3On"
    private Button btnLuce3On; // Value injected by FXMLLoader

    @FXML // fx:id="btnLuce3Off"
    private Button btnLuce3Off; // Value injected by FXMLLoader

    @FXML // fx:id="btnTappUp"
    private Button btnTappUp; // Value injected by FXMLLoader

    @FXML // fx:id="btnTappaDown"
    private Button btnTappaDown; // Value injected by FXMLLoader
    
   

    @FXML
    void luceAllOff(ActionEvent event) {
    		model.getLuceAll().TurnOff();
    }

    @FXML
    void luceAllOn(ActionEvent event) {
    		model.getLuceAll().TurnOn();
    }
    
    
    @FXML
    void luceUnoOff(ActionEvent event) {
    		model.getLuce1().TurnOff();
    }

    @FXML
    void luceUnoOn(ActionEvent event) {
    		model.getLuce1().TurnOn();
    }

    @FXML
    void luceDueOff(ActionEvent event) {
    		model.getLuce2().TurnOff();
    }

    @FXML
    void luceDueOn(ActionEvent event) {
    		model.getLuce2().TurnOn();
    }
    
    
    @FXML
    void luceTreOff(ActionEvent event) {
    		model.getLuce3().TurnOff();
    	}

    @FXML
    void luceTreOn(ActionEvent event) {
    		model.getLuce3().TurnOn();
    }

    @FXML
    void tapparellaDown(ActionEvent event) {
    		model.getTapparella().down();
    }

    @FXML
    void tapparellaStop(ActionEvent event) {
    		model.getTapparella().stop();
    }

    @FXML
    void tapparellaUp(ActionEvent event) {
    		model.getTapparella().up();
    }

    
    void setModel(Model model) {
    		this.model = model;
    }


	@FXML // This method is called by the FXMLLoader when initialization is complete
	    void initialize() {
		assert btnLuceAllOn != null : "fx:id=\"btnLuceAllOn\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnLuceAllOff != null : "fx:id=\"btnLuceAllOff\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnLuce1On != null : "fx:id=\"btnLuce1On\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnLuce1Off != null : "fx:id=\"btnLuce1Off\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnLuce2On != null : "fx:id=\"btnLuce2On\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnLuce2Off != null : "fx:id=\"btnLuce2Off\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnLuce3On != null : "fx:id=\"btnLuce3On\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnLuce3Off != null : "fx:id=\"btnLuce3Off\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnTappUp != null : "fx:id=\"btnTappUp\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
	    }
	}
