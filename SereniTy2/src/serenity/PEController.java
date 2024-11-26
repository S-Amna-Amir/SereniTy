package serenity;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PEController 
{
	 private Stage stage;
	    private Scene scene;
	    private Parent root;

	    @FXML
		public void switchToHighlights(ActionEvent event) throws IOException
		{
			root = FXMLLoader.load(getClass().getResource("Higlights.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	    
	    public void switchToJournal(ActionEvent event) throws IOException
		{
			root = FXMLLoader.load(getClass().getResource("Journal.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	    
	    public void switchToExercises(ActionEvent event) throws IOException
		{
			root = FXMLLoader.load(getClass().getResource("Exercises.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}

}
