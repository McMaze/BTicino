package it.polito.bticino.main;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.bticino.lib.Light.LightStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    
    @FXML // fx:id="statusLights"
    private TextField statusLights; // Value injected by FXMLLoader

    @FXML // fx:id="statusLight1"
    private TextField statusLight1; // Value injected by FXMLLoader

    @FXML // fx:id="statusLight2"
    private TextField statusLight2; // Value injected by FXMLLoader

    @FXML // fx:id="statusLight3"
    private TextField statusLight3; // Value injected by FXMLLoader

    

    @FXML
    void luceAllOff(ActionEvent event) {
    		model.getLuceAll().TurnOff();
    		this.statusLights.setText(model.getLuceAll().getStato().toString());

    }

    @FXML
    void luceAllOn(ActionEvent event) {
    		model.getLuceAll().TurnOn();
    		this.statusLights.setText(model.getLuceAll().getStato().toString());
    }
    
    
    @FXML
    void luceUnoOff(ActionEvent event) {
    		model.getLuce1().TurnOff();
    		if (model.getLuce1().getStato() == LightStatus.OFF) {
    			this.statusLight1.clear();
    			this.statusLight1.setText(model.getLuce1().getStato().toString());
    		}
    }

    @FXML
    void luceUnoOn(ActionEvent event) {
    		model.getLuce1().TurnOn();
    		if (model.getLuce1().getStato() == LightStatus.ON){
    			this.statusLight1.clear();
    			this.statusLight1.setText(model.getLuce1().getStato().toString());
    		}
    }

    @FXML
    void luceDueOff(ActionEvent event) {
    		model.getLuce2().TurnOff();
    		if (model.getLuce2().getStato() == LightStatus.OFF) {
    			this.statusLight2.clear();
    			this.statusLight2.setText(model.getLuce2().getStato().toString());
    		}
    }

    @FXML
    void luceDueOn(ActionEvent event) {
    		model.getLuce2().TurnOn();
    		if (model.getLuce2().getStato() == LightStatus.ON) {
    			this.statusLight2.clear();
    			this.statusLight2.setText(model.getLuce2().getStato().toString());
    		}
    }
    
    
    @FXML
    void luceTreOff(ActionEvent event) {
    		model.getLuce3().TurnOff();
    		if (model.getLuce3().getStato() == LightStatus.OFF) {
    			this.statusLight3.clear();
    			this.statusLight3.setText(model.getLuce3().getStato().toString());
    		}
    	}

    @FXML
    void luceTreOn(ActionEvent event) {
    		model.getLuce3().TurnOn();
    		if (model.getLuce3().getStato() == LightStatus.ON) {
    			this.statusLight3.clear();
    			this.statusLight3.setText(model.getLuce3().getStato().toString());
    		}
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
    		this.model.getStati();
    		this.statusLights.setText(model.getLuceAll().getClass().toString());
    		this.statusLight1.setText(model.getLuce1().getStato().toString());
    		this.statusLight2.setText(model.getLuce2().getStato().toString());
    		this.statusLight3.setText(model.getLuce3().getStato().toString());
 
    }
    
    
   /* private void setButtons() {
		
    		if (model.getLuce1().getStato().compareTo(LightStatus.ON)==0) {
    			this.btnLuce1On.setDisable(true);
    		} else {
    			this.btnLuce1Off.setDisable(true);
    		}
    		if (model.getLuce2().getStato().compareTo(LightStatus.ON)==0) {
    			this.btnLuce2On.setDisable(true);
    		} else {
    			this.btnLuce2Off.setDisable(true);
    		}
    		if (model.getLuce3().getStato().compareTo(LightStatus.ON)==0) {
    			this.btnLuce3On.setDisable(true);
    		} else {
    			this.btnLuce3Off.setDisable(true);
    		}
    		
	}*/

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
        assert btnTappaDown != null : "fx:id=\"btnTappaDown\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert statusLight1 != null : "fx:id=\"statusLight1\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert statusLights != null : "fx:id=\"statusLights\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert statusLight2 != null : "fx:id=\"statusLight2\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert statusLight3 != null : "fx:id=\"statusLight3\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
	    }
	}
