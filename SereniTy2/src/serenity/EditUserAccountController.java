package serenity;
/* Maha */

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class EditUserAccountController {
    @FXML private TextField tf_username; 
    @FXML private PasswordField tf_newpassword;  
    private Admin admin; 

    public void initialize(Admin loggedInAdmin) {
        this.admin = loggedInAdmin;
		System.out.println("edit initialised controller");
    }

    @FXML
    private void btn_save_clicked() {
		System.out.println("save clicked");
        String username = tf_username.getText();
        String newPassword = tf_newpassword.getText();

        if (username.isEmpty() || newPassword.isEmpty()) {
        	show_alert("Please fill in both fields.", AlertType.WARNING);
            return;
        }

        boolean success = admin.edit_user_account(username, username, newPassword);
        if (success) {
        	show_alert("User account updated successfully!", AlertType.INFORMATION);
        } 
        else {
        	show_alert("Failed to update user account.", AlertType.ERROR);
        }
    }

    private void show_alert(String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Account Update");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
