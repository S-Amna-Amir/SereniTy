package serenity;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ViewEntryController
{

	  private Stage stage;
	    private Scene scene;
	    private Parent root;
    @FXML
    private TextArea EntryText;

    @FXML
  	public void switchToHighlights(ActionEvent event) throws IOException
  	{
  		root = FXMLLoader.load(getClass().getResource("Higlights.fxml"));
  		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  		scene = new Scene(root);
  		stage.setScene(scene);
  		stage.show();
  	}
      
      @FXML
      public void switchToJEntry(ActionEvent event) throws IOException
  	{
  		root = FXMLLoader.load(getClass().getResource("JEntry.fxml"));
  		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  		scene = new Scene(root);
  		stage.setScene(scene);
  		stage.show();
  	}
      
      @FXML
      public void switchToExercises(ActionEvent event) throws IOException
  	{
  		root = FXMLLoader.load(getClass().getResource("Exercises.fxml"));
  		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  		scene = new Scene(root);
  		stage.setScene(scene);
  		stage.show();
  	}
    public void setTextInTextField(String text) 
    {
        EntryText.setText(text);  // Sets the provided string in the TextField
    }

   

}

