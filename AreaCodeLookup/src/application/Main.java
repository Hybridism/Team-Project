package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * This class contains the entry point for the program as well as the code to inflate the GUI layout and set the content view.
 * @author Justin Elson mwt954
 */
public class Main extends Application {
	/**
	 * This method inflates the GUI layout and sets the content view.
	 * @param primaryStage - the stage on which we draw the layout
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("../application/view/GUI.fxml"));
			Scene scene = new Scene(root,600,480);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is the program entry point.
	 * @param args - command line arguments passed in as an array of strings
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
