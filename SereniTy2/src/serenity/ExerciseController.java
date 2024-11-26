package serenity;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ExerciseController 
{
	private Stage stage;
    private Scene scene;
    private Parent root;
    private ArrayList <Exercise> ex;
    
    @FXML
    private TableView<Exercise> ExTable;

    @FXML
    private TableColumn<Exercise, Integer> colEntryID;
    
    @FXML
    private TableColumn<Exercise, String> colEntry;
    
    @FXML
    private TableColumn<Exercise, Boolean> colPrac;
    
    private ObservableList<Exercise> highlightsObservableList;


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
    
    public void switchToPracExercises(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("PracticeExercise.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
    
    @FXML
    public void initialize() 
    {
    	
    	Creator c = Creator.getInstance();
    	ExList EL = c.getExlist();
    	ex = EL.getExercises();
    	
        // Bind columns
        colEntryID.setCellValueFactory(new PropertyValueFactory<>("eid"));
        colEntry.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colPrac.setCellValueFactory(new PropertyValueFactory<>("practiced"));

        // Populate the ObservableList
        highlightsObservableList = FXCollections.observableArrayList(ex);

        // Set data to the TableView
        ExTable.setItems(highlightsObservableList);
    }

    public void updateTable() 
    {
        highlightsObservableList.setAll(ex);
    }


}
