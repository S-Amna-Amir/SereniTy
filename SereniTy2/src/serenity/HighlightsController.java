package serenity;

import java.util.ArrayList;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HighlightsController 
{
	@FXML
    private AnchorPane HighPane;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<JournalEntry> HighTable;

    @FXML
    private TableColumn<JournalEntry, Integer> colEntryID;

    @FXML
    private TableColumn<JournalEntry, String> colDate;

    @FXML
    private TableColumn<JournalEntry, String> colEntry;

    private ObservableList<JournalEntry> highlightsObservableList;
    private UserJournal UJ;
    private ArrayList<JournalEntry> highlights;

    @FXML
    public void initialize() 
    {
    	Creator c = Creator.getInstance();
    	UJ = c.getJournal();
    	highlights = UJ.getHighlights();
    	
        // Bind columns
        colEntryID.setCellValueFactory(new PropertyValueFactory<>("Jid"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEntry.setCellValueFactory(new PropertyValueFactory<>("text"));

        // Populate the ObservableList
        highlightsObservableList = FXCollections.observableArrayList(highlights);

        // Set data to the TableView
        HighTable.setItems(highlightsObservableList);
    }

    public void updateTable() {
        highlightsObservableList.setAll(highlights);
    }
    
    public void setUserJournal(UserJournal userJournal) 
    {
        this.UJ = userJournal;
        this.highlights = UJ.getHighlights();
    }
    
    @FXML
    void switchToJournal(ActionEvent event) throws IOException
    {
    	root = FXMLLoader.load(getClass().getResource("Journal.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene((scene));
    	stage.show();
    }

    @FXML
    public void switchToExercises(ActionEvent event) throws IOException
    {
    	root = FXMLLoader.load(getClass().getResource("Exercises.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene((scene));
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
}


/*
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

public class HighlightsController 
{

    @FXML
    private AnchorPane HighPane;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void switchToJournal(ActionEvent event) throws IOException
    {
    	root = FXMLLoader.load(getClass().getResource("Journal.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene((scene));
    	stage.show();
    }

    public void switchToExercises(ActionEvent event) throws IOException
    {
    	root = FXMLLoader.load(getClass().getResource("Exercises.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene((scene));
    	stage.show();
    }

    public void switchToJEntry(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("JEntry.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
 

}
*/