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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SceneController {

    @FXML
    private AnchorPane JournalPane;

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
    private ArrayList<JournalEntry> pages;

    // Variable to store the currently selected journal entry
    private JournalEntry selectedJournalEntry;

    @FXML
    public void initialize() {
        Creator c = Creator.getInstance();
        UJ = c.getJournal();
        pages = UJ.getPages();

        // Bind columns
        colEntryID.setCellValueFactory(new PropertyValueFactory<>("Jid"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEntry.setCellValueFactory(new PropertyValueFactory<>("text"));

        // Populate the ObservableList
        highlightsObservableList = FXCollections.observableArrayList(pages);

        // Set data to the TableView
        HighTable.setItems(highlightsObservableList);

        // Add a listener for row selection
        HighTable.setOnMouseClicked(event -> 
        {
            selectedJournalEntry = HighTable.getSelectionModel().getSelectedItem();
            if (selectedJournalEntry != null) 
            {
                System.out.println("Selected Entry ID: " + selectedJournalEntry.getJid());
                System.out.println("Selected Entry Date: " + selectedJournalEntry.getDate());
                System.out.println("Selected Entry Text: " + selectedJournalEntry.getText());
            }
        });
    }

    public void updateTable() {
        highlightsObservableList.setAll(pages);
    }

    @FXML
    public void switchToHighlights(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Highlights.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToJEntry(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("JEntry.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToExercises(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Exercises.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToViewEntry(ActionEvent event) throws IOException 
    {
        // Ensure an entry is selected before switching screens
        if (selectedJournalEntry != null) 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewEntry.fxml"));
            root = loader.load();

            // Get the controller instance from the loader
            ViewEntryController viewEntryController = loader.getController();

            // Pass the selected journal entry's text to the controller
            viewEntryController.setTextInTextField(selectedJournalEntry.getText());

            // Switch to the new scene
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } 
        else 
        {
            System.out.println("No journal entry selected. Please select an entry before viewing.");
        }
    }
}
