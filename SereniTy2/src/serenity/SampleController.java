package serenity;
/*Maha */


import application.utils.Global;
import application.utils.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SampleController {

    @FXML private TextField tf_username;
    @FXML private PasswordField tf_password;
    @FXML private Label lbl_message_username;
    @FXML private Label lbl_message_password;
    @FXML public CheckBox isAdmin;

    private Stage currentStage;  // Stage reference

    // Method to set the Stage
    public void setStage(Stage stage) {
        this.currentStage = stage;  // Initialize the stage here
    }

    @FXML
    public boolean isAdminClicked(ActionEvent event) {
        if (isAdmin.isSelected()) {
            System.out.println("Admin mode");
            return true;
        } else {
            System.out.println("User mode");
        }
        return false;
    }

    public boolean validateInputCredentials() {
        String username = tf_username.getText();
        String password = tf_password.getText();
        String usernameError = Validation.validateTextField(username, 8); //min length is 8
        String passwordError = Validation.validateTextField(password, 8); //min length is 8
        lbl_message_username.setText("");
        lbl_message_password.setText("");
        boolean isValid = true;
        if (usernameError != null) {
            lbl_message_username.setText("Username error: " + usernameError);
            isValid = false;
        }
        if (passwordError != null) {
            lbl_message_password.setText("Password error: " + passwordError);
            isValid = false;
        }
        return isValid;
    }

    @FXML
    public void btn_login_clicked(ActionEvent event) {
        if (validateInputCredentials()) {
            String username = tf_username.getText();
            String password = tf_password.getText();
            boolean isAdmin = isAdminClicked(event);
            String roleType = PersistenceHandler3.authenticateUser(username, password, isAdmin);

            if (roleType == null) {
                lbl_message_username.setText("Invalid username or password.");
                return;
            }

            String homePageFile = "Admin".equalsIgnoreCase(roleType) ? "AdminHomePage.fxml" : "ActualHomePage.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(homePageFile));
            try {
                AnchorPane homePageRoot = loader.load();
                Stage currentStage = (this.currentStage != null) ? this.currentStage :   (Stage) tf_username.getScene().getWindow();  

                if ("Admin".equalsIgnoreCase(roleType)) {
                    AdminHomePageController adminController = loader.getController();
                    Admin admin = PersistenceHandler3.getAdminByUsername(username);
                    adminController.initialize(admin, currentStage);
                } else {
                    ActualHomePageController userController = loader.getController();
                    User user = PersistenceHandler3.getUserByUsername(username);
                    userController.initialize(user, currentStage);
                }

                Scene homePageScene = new Scene(homePageRoot);
                currentStage.setScene(homePageScene);
                currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void isAdminClicked1(ActionEvent event) {
        boolean adminMode = isAdmin.isSelected();
        Global.getInstance().setAdmin(adminMode);
        if (adminMode) {
            System.out.println("Admin interface");
        } else {
            System.out.println("User interface");
        }
    }

    @FXML
    public void btn_signup_clicked(ActionEvent event) {
        System.out.println("- SignUp button clicked -");
        String fxmlFile = isAdmin.isSelected() ? "AdminSignUp.fxml" : "SignUp.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            AnchorPane signUpRoot = loader.load();
            Scene signUpScene = new Scene(signUpRoot);
            Stage currentStage = (Stage) tf_username.getScene().getWindow();
            currentStage.setScene(signUpScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
