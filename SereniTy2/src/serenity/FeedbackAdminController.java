package serenity;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class FeedbackAdminController {

    @FXML
    private TextArea feedbackTextArea; // For entering feedback

    @FXML
    private TextArea feedbackDisplayArea; // For displaying saved feedback

    @FXML
    private Button saveButton; // To save the feedback
    @FXML
    private Button backButton; // To go back to the admin menu

    @FXML
    private void handleSaveFeedback() {
        String feedbackText = feedbackTextArea.getText();
        if (feedbackText.isEmpty()) {
            showAlert("Feedback is empty", "Please enter some feedback before saving.");
        } else {
            // Simulate saving feedback (print to console)
            System.out.println("Feedback Saved: " + feedbackText);
            showAlert("Feedback Saved", "Feedback has been successfully saved.");
            feedbackTextArea.clear(); // Clear the TextArea after saving
            displayFeedbackInConsoleAndUI(); // Update the console and UI
        }
    }

    @FXML
    private void displayFeedbackInConsoleAndUI() {
        // Simulate fetching all feedback
        String feedbackList = "Sample Feedback 1\nSample Feedback 2\nSample Feedback 3"; // Example feedback list

        if (feedbackList.isEmpty()) {
            showAlert("No Feedback Available", "No feedback records found in the system.");
            return;
        }

        // Display feedback on the console
        System.out.println("\n--- Feedback List ---");
        System.out.println(feedbackList);

        // Display feedback in the TextArea
        feedbackDisplayArea.setText(feedbackList); // Replace with actual fetched feedback in a real scenario
    }

    @FXML
    private void goBackToAdminMenu() {
        try {
            // Load the admin menu form (admin_menu.fxml)
            AnchorPane adminMenuForm = FXMLLoader.load(getClass().getResource("admin_menu.fxml"));
            Scene scene = new Scene(adminMenuForm, 400, 400);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene); // Change scene back to admin menu
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
