package it.polito.bticino.main;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.bticino.lib.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;



public class BTicinoController {

	
	public Model model; 
	public Thread comandi;
	//public Thread eventi;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnLuceGeneraleOn"
    private Button btnLuceGeneraleOn; // Value injected by FXMLLoader

    @FXML // fx:id="btnLuceGeneraleOff"
    private Button btnLuceGeneraleOff; // Value injected by FXMLLoader

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
    
    @FXML // fx:id="statusLuceGenerale"
    private TextField statusLuceGenerale; // Value injected by FXMLLoader
    
    @FXML // fx:id="statusLuce1"
    private TextField statusLuce1; // Value injected by FXMLLoader

    @FXML // fx:id="statusLuce2"
    private TextField statusLuce2; // Value injected by FXMLLoader

    @FXML // fx:id="statusLuce3"
    private TextField statusLuce3; // Value injected by FXMLLoader

    @FXML // fx:id="statusTapparella"
    private TextField statusTapparella; // Value injected by FXMLLoader

    
   

    @FXML
    void luceGeneraleOff(ActionEvent event) {
    		model.getLuceGenerale().TurnOff();
    }

    @FXML
    void luceGeneraleOn(ActionEvent event) {
    		model.getLuceGenerale().TurnOn();
    }
    
    
    @FXML
    void luceUnoOff(ActionEvent event) {
    		model.getLuce1().TurnOff();
    		// sincronizzazione ? 
    		this.statusLuce1.setText(model.getLuce1().getStato().getStatusName().toString());
    }

    @FXML
    void luceUnoOn(ActionEvent event) {
    		model.getLuce1().TurnOn();
    		// sincronizzazione ?
    		this.statusLuce1.setText(model.getLuce1().getStato().getStatusName().toString());
    }

    @FXML
    void luceDueOff(ActionEvent event) {
    		model.getLuce2().TurnOff();
    		// sincronizzazione ?
    		this.statusLuce2.setText(model.getLuce2().getStato().getStatusName().toString());
    }

    @FXML
    void luceDueOn(ActionEvent event) {
    		model.getLuce2().TurnOn();
    		// sincronizzazione ?
    		this.statusLuce2.setText(model.getLuce2().getStato().getStatusName().toString());
    }
    
    
    @FXML
    void luceTreOff(ActionEvent event) {
    		model.getLuce3().TurnOff();
    		// sincronizzazione ?
    		this.statusLuce3.setText(model.getLuce3().getStato().getStatusName().toString());
    	}

    @FXML
    void luceTreOn(ActionEvent event) {
    		model.getLuce3().TurnOn();
    		// sincronizzazione ?
    		this.statusLuce3.setText(model.getLuce3().getStato().getStatusName().toString());
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

    
    void set(Model model, Thread threadComandi) {
    		this.model = model;
    		this.comandi = threadComandi;
    		this.setStausLabel();	
    }


	private void setStausLabel() {
		this.statusLuce1.setText(this.model.getLuce1().getStato().getStatusName().toString());
		this.statusLuce2.setText(this.model.getLuce2().getStato().getStatusName().toString());
		this.statusLuce3.setText(this.model.getLuce3().getStato().getStatusName().toString());
		this.statusTapparella.setText(this.model.getTapparella().getStato().getStatusName().toString());
		
		model.setStatoLuceGenerale();
		this.statusLuceGenerale.setText(this.model.getLuce3().getStato().getStatusName().toString());
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	    void initialize() {
		assert btnLuceGeneraleOn != null : "fx:id=\"btnLuceGeneraleOn\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnLuceGeneraleOff != null : "fx:id=\"btnLuceGeneraleOff\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnLuce1On != null : "fx:id=\"btnLuce1On\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnLuce1Off != null : "fx:id=\"btnLuce1Off\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnLuce2On != null : "fx:id=\"btnLuce2On\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnLuce2Off != null : "fx:id=\"btnLuce2Off\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnLuce3On != null : "fx:id=\"btnLuce3On\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnLuce3Off != null : "fx:id=\"btnLuce3Off\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert btnTappUp != null : "fx:id=\"btnTappUp\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert statusLuceGenerale != null : "fx:id=\"statusLuceGenerale\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert statusLuce1 != null : "fx:id=\"statusLuce1\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert statusLuce2 != null : "fx:id=\"statusLuce2\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert statusLuce3 != null : "fx:id=\"statusLuce3\" was not injected: check your FXML file 'BTicinoGest.fxml'.";
        assert statusTapparella != null : "fx:id=\"statusTapparella\" was not injected: check your FXML file 'BTicinoGest.fxml'.";

	}
	
	
	 
	}
