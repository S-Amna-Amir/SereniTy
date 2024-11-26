package serenity;
/* Maha */

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DeleteUserController {

    @FXML private TextField tf_username;
    private Admin admin; 

    public void initialize(Admin admin) {
        this.admin = admin;
    }

    @FXML
    public void btn_delete_user_clicked() {
        String username = tf_username.getText();
        if (username.isEmpty()) {
        	show_alert(Alert.AlertType.ERROR, "Validation Error", "Username field must be filled out.");
            return;
        }
       
        boolean success = admin.delete_user(username);
        if (success) {
        	show_alert(Alert.AlertType.INFORMATION, "Success", "User deleted successfully!");
            go_to_admin_homepage();
        } else {
        	show_alert(Alert.AlertType.ERROR, "Error", "Failed to delete the user. Please try again.");
        }
    }

    private void show_alert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void go_to_admin_homepage() {
        try {
            Stage currentStage = (Stage) tf_username.getScene().getWindow();
            currentStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHomePage.fxml"));
            AnchorPane root = loader.load();

            AdminHomePageController controller = loader.getController();
            controller.initializeAdminDashboard(admin);

            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Admin Home Page");
            currentStage.show();

        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

