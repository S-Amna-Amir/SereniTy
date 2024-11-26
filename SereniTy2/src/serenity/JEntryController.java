package serenity;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class JEntryController 
{    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TextArea EntryText; // Reference to the TextArea in JEntry.fxml

    @FXML
    private ComboBox<String> MoodCombo; // Reference to the ComboBox in JEntry.fxml

    @FXML
    public void initialize() 
    {
        // Populate the ComboBox with moods
        ObservableList<String> moods = FXCollections.observableArrayList(
            "anger", "fear", "anxiety", "sadness", "grateful", "hopeful", "excited", "happy"
        );
        MoodCombo.setItems(moods);
    }

    // Method to get the text from EntryText
    public String getEntryText() 
    {
        return EntryText.getText();
    }

    // Method to get the selected mood from MoodCombo
    public String getSelectedMood() 
    {
        return MoodCombo.getValue();
    }

    // Method to clear the TextArea and ComboBox after saving or resetting
    public void clearFields() 
    {
        EntryText.clear();
        MoodCombo.setValue(null); // Resets the ComboBox selection
    }


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
    public void switchToJournal(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Journal.fxml"));
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
    
    
    @FXML
    public void saveEntry(ActionEvent event) throws IOException
    {
    	
    	int i = Creator.getJECounter();
    	String id = "" + i;
    	Date d = Date.valueOf(LocalDate.now());
    	String s = getEntryText();
    	Boolean b = false;
    	if(getSelectedMood().equals("happy") || getSelectedMood().equals("grateful") || getSelectedMood().equals("hopeful") || getSelectedMood().equals("excited"))
    	{
    		b = true;
    	}
    	JournalEntry j = new JournalEntry(id, s, d, b);
    	UserJournal U = Creator.getJournal();
    	U.AddEntry(j);
    	clearFields();
    }
    

}

