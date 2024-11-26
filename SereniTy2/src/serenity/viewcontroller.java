package serenity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.EventObject;
import java.util.List;

public class viewcontroller {

    @FXML
    private ListView<String> goalsListView; // A ListView to display goals
    @FXML
    private Button backButton;

    private List<String> goalsList;
	private EventObject event;

    // Method to populate the ListView with goal data
    public void setGoalsList(List<String> goalsList) {
        this.goalsList = goalsList;
        populateGoalsListView();
    }

    private void populateGoalsListView() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (String goal : goalsList) {
            observableList.add(goal);
        }
        goalsListView.setItems(observableList);
    }

    @FXML
    public void goBackToGoalsForm(ActionEvent event) {
        loadForm("goals.fxml", 400, 400);
    }

    private void loadForm(String fxmlFile, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            AnchorPane goalsForm = loader.load();
            Scene scene = new Scene(goalsForm, width, height);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
