package serenity;
/* Maha */

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class EditMyAccountController {

    @FXML private PasswordField tf_oldpassword;
    @FXML private PasswordField tf_newpassword;
    private User user; 
    private Stage currentStage; 

    public void initialize(User loggedInUser, Stage currentStage) {
        this.user = loggedInUser;
        this.currentStage = currentStage; 
    }

    @FXML
    private void btn_save_clicked() {
        String oldPassword = tf_oldpassword.getText();
        String newPassword = tf_newpassword.getText();

        if (oldPassword.isEmpty() || newPassword.isEmpty()) {
        	show_alert1("Please fill in both fields.");
            return;
        }

        String currentPassword = user.getPassword();

        if (oldPassword.equals(currentPassword)) {
        	boolean success = user.editAccount(oldPassword, newPassword);
        	if (success) {
        		show_alert("Password updated successfully!", Alert.AlertType.INFORMATION);
            } 
        	else {
        		show_alert("Failed to update password.", Alert.AlertType.ERROR);
            }
        } 
        else {
        	show_alert("Incorrect old password.", Alert.AlertType.ERROR);
        }
    }

    private void show_alert1(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Password Update");
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void show_alert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Password Update");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
