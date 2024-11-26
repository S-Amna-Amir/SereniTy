package serenity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

public class goalscontroller {

    // Method for handling the "Back" button click to go to S1.fxml
    @FXML
    public void goBackToS1(ActionEvent event) {
        loadForm("S1.fxml", 394, 392);
    }

    // Method for handling the "Add" button click to go to Add form
    @FXML
    public void goToAddGoalForm(ActionEvent event) {
        loadForm("add.fxml", 400, 400);
    }

    // Method for handling the "Edit" button click to go to Edit form
    @FXML
    public void goToEditForm(ActionEvent event) {
        loadForm("edit.fxml", 400, 400);
    }

    // Method for handling the "Delete" button click to go to Delete form
    @FXML
    public void goToDeleteForm(ActionEvent event) {
        loadForm("delete.fxml", 400, 400);
    }

    // Method for handling the "View" button click to go to View form
    @FXML
    public void goToViewForm(ActionEvent event) {
        loadForm("view.fxml", 400, 400);
    }

    // Helper method to load forms and switch scenes
    private void loadForm(String fxmlFile, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));  // Adjust the path if needed
            AnchorPane form = loader.load();
            Scene scene = new Scene(form, width, height);
            Stage stage = (Stage) ((javafx.scene.Node) scene.getRoot()).getScene().getWindow();
            stage.setScene(scene);  // Switch to the new scene
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading form: " + fxmlFile);
        }
    }
}
