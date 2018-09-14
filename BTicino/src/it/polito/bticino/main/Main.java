package it.polito.bticino.main;
	
import it.polito.bticino.lib.Model;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("BTicinoGest.fxml")) ;
			BorderPane root = (BorderPane)loader.load();
			BTicinoController controller = loader.getController();
			
	
				Model model = new Model(controller);
				controller.set(model);
				
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.show();
				
				if (BTicinoController.inizializzazione==true) {
					Thread eventi = new Thread (model.sockMonitor, "eventi");
					eventi.start();
					//controller.updateLabel();
					}
			
		
    			primaryStage.setOnCloseRequest((new EventHandler<WindowEvent>() {
    				public void handle(WindowEvent we){
    					model.getSocketMonitor().stopRunning();
    					model.getSocket().close();
    					
    				}
    			}));
    			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
