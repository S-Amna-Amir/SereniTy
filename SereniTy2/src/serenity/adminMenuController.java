package serenity;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class adminMenuController {

    @FXML
    private javafx.scene.control.Button ReportButton;

    @FXML
    private javafx.scene.control.Button FeedbackButton;

    @FXML
    private javafx.scene.control.Button BackButton;

    @FXML
    public void showReport() {
        // Load the Report form when the "Report" button is clicked
        navigateTo("Report_admin.fxml", "Report");
    }

    @FXML
    public void showFeedback() {
        // Load the Feedback form when the "Feedback" button is clicked
        navigateTo("Feedback_admin.fxml", "Feedback");
    }

    @FXML
    public void BackButton() {
        // Load the Goals form when the "Back" button is clicked
        navigateTo("goals.fxml", "Goals");
    }

    private void navigateTo(String fxmlFile, String title) {
        try {
            // Load the FXML file specified
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            AnchorPane root = loader.load();

            // Get the current stage from the button triggering the action
            Stage stage = (Stage) BackButton.getScene().getWindow();
            stage.setScene(new Scene(root, 400, 400));
            stage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace(); // Print the error to the console for debugging
        }
    }
}
