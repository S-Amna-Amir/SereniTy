package serenity;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application 
{
	
	public static void main(String[] args) 
	{
		PersistenceHandler p = new DBHandler();
		
		launch(args);
		
	}
	
	  @Override
	    public void start(Stage primaryStage) {
	        try {

	        	Creator c = Creator.getInstance();
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
	            Parent root = loader.load();
	            SampleController controller = loader.getController();
	            controller.setStage(primaryStage);
	            
	            Scene scene = new Scene(root);
	            primaryStage.setScene(scene);
	            primaryStage.setTitle("SereniTy by TechPocket");
	            primaryStage.show();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	   
}
