package serenity;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ReportAdminController {

    @FXML
    private Label userIdLabel;
    @FXML
    private Label goalsAchievedLabel;
    @FXML
    private Label exercisesPerformedLabel;
    @FXML
    private Label streakCountLabel;
    @FXML
    private Label reportGeneratedAtLabel;
    
    @FXML
    private TextField inputTextBox; // TextBox for input

	/*
	 * @FXML public void initialize() { // Example: Load the report for a user
	 * (static userId for demo) String userId = "exampleUser123"; // Static userId
	 * for demo Report report = new Report(userId, 5, 20, 10); // Example Report
	 * (replace with actual logic)
	 * 
	 * if (report != null) { // Populate the labels with report data
	 * userIdLabel.setText("User ID: " + report.getUserId());
	 * goalsAchievedLabel.setText("Goals Achieved: " + report.getGoalsAchieved());
	 * exercisesPerformedLabel.setText("Exercises Performed: " +
	 * report.getExercisesPerformed()); streakCountLabel.setText("Streak Count: " +
	 * report.getStreakCount());
	 * reportGeneratedAtLabel.setText("Report Generated At: " +
	 * report.getGeneratedTime()); } else { showAlert(Alert.AlertType.INFORMATION,
	 * "Report not found", "No report was found for the user."); } }
	 */

    // Method to display the report on the console (or do nothing for the "Save" button)
    @FXML
    public void saveReport(ActionEvent event) {
        // Do nothing for now
        System.out.println("Save button clicked, but no action is performed.");
    }

    // Action to handle the "Back" button to go to the Admin Menu
    @FXML
    public void goBackToAdminMenu(ActionEvent event) {
        try {
            // Load the Admin Menu form (admin_menu.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_menu.fxml"));
            AnchorPane adminMenuForm = loader.load();
            Scene scene = new Scene(adminMenuForm, 400, 400);
            Stage stage = (Stage) userIdLabel.getScene().getWindow();
            stage.setScene(scene); // Change scene back to admin menu
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
