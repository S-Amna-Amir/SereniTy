package serenity;
/* Maha */

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddUserController {

	@FXML private TextField tf_username;
	@FXML private TextField tf_password;
	@FXML private TextField tf_firstName;
	@FXML private TextField tf_lastName;
    @FXML private TextField tf_email;
    @FXML private TextField tf_age;
    private Admin admin;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    

    public void initialize(Admin admin) {
        this.admin = admin;
    }
    
    @FXML
    public void switchToAHomePage(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

    @FXML
    public void btn_add_userClicked() {
        String username = tf_username.getText();
        String password = tf_password.getText();
        String firstName = tf_firstName.getText();
        String lastName = tf_lastName.getText();
        String email = tf_email.getText();
        String ageText = tf_age.getText();

        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || ageText.isEmpty()) {
        	show_alert(Alert.AlertType.ERROR, "Validation Error", "Fields are empty.");
            return;
        }

        float age;
        try {
            age = Float.parseFloat(ageText);
            if (age <= 0) {
                throw new NumberFormatException("Age must be greater than 0.");
            }
        } 
        catch (NumberFormatException e) {
        	show_alert(Alert.AlertType.ERROR, "Validation Error", "Please enter a valid number.");
            return;
        }

        int accountId = admin.add_user(username, password, firstName, lastName, email, age);

        if (accountId > 0) {
        	show_alert(Alert.AlertType.INFORMATION, "Success", "User added successfully! Account ID: " + accountId);
            go_to_admin_homepage();
        } 
        else {
        	show_alert(Alert.AlertType.ERROR, "Error", "Failed to add the user. Please try again.");
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
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHomePage.fxml"));
            AnchorPane root = loader.load();

            AdminHomePageController controller = loader.getController();
            controller.initialize(admin, currentStage);

            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Admin Home Page");
            currentStage.show();
            System.out.println("admin homepage loaded");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}

