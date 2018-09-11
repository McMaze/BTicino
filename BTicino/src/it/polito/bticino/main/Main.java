package it.polito.bticino.main;
	
import it.polito.bticino.lib.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("BTicinoGest.fxml")) ;
			BorderPane root = (BorderPane)loader.load();
			BTicinoController controller = loader.getController();
			
			Thread t = Thread.currentThread();
			t.setName("comandi");
			
			Model model = new Model();
			controller.set(model, t );
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
