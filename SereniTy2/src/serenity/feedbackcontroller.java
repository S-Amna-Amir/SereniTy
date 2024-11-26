package serenity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class feedbackcontroller {

    @FXML
    private TextArea feedbackTextArea;

    // Action for the Save button
    @FXML
    public void saveFeedback(ActionEvent event) {
        String feedback = feedbackTextArea.getText().trim();

        // Check if feedback is empty
        if (feedback.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Feedback cannot be empty.");
            return;
        }

        // Simulate saving feedback (print to console)
        System.out.println("Feedback Saved: " + feedback);

        // Display success message
        showAlert(Alert.AlertType.INFORMATION, "Success", "Feedback saved successfully.");

        // Clear the TextArea after saving
        feedbackTextArea.clear();
    }

    // Action for the Back button
    @FXML
    public void goBackToPreviousForm(ActionEvent event) {
        try {
            // Load the goals form (goals.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("goals.fxml"));
            AnchorPane goalsForm = loader.load();

            // Create the scene for the goals form
            Scene scene = new Scene(goalsForm, 400, 400);
            Stage stage = (Stage) feedbackTextArea.getScene().getWindow();
            stage.setScene(scene); // Change the scene back to goals form
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Utility method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
