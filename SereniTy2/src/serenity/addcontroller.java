package serenity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class addcontroller {

    @FXML
    private TextArea goalDescription;

    public void setGoalDescription(String description) {
        goalDescription.setText(description != null ? description : "");
    }

    @FXML
    public void saveGoal(ActionEvent event) {
        String updatedGoalDescription = goalDescription.getText().trim();
        if (updatedGoalDescription.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Goal description cannot be empty.");
            return;
        }
        // Save goal logic
        showAlert(Alert.AlertType.INFORMATION, "Success", "Goal saved successfully.");
        navigateTo("goals.fxml", event);
    }

    @FXML
    public void goBackToGoals(ActionEvent event) {
        navigateTo("goals.fxml", event);
    }

    private void navigateTo(String fxmlFile, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            AnchorPane form = loader.load();
            Scene scene = new Scene(form, 400, 400);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
