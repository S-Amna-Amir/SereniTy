package serenity;

/* Maha */

import application.utils.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminSignUpController {
    @FXML private TextField tf_username;
    @FXML private TextField tf_password;
    @FXML private TextField tf_firstName;
    @FXML private TextField tf_lastName;
    @FXML private TextField tf_email;
    @FXML private Label lbl_message_username;
    @FXML private Label lbl_message_password;
    @FXML private Label lbl_message_email;
    @FXML private Label lbl_message_firstName;
    @FXML private Label lbl_message_lastName;
    
    PersistenceHandler3 ph;
    
    public boolean validateInputCredentials() {
        String username = tf_username.getText();
        String password = tf_password.getText();
        String firstName = tf_firstName.getText();
        String lastName = tf_lastName.getText();
        String email = tf_email.getText();
        lbl_message_username.setText("");
        lbl_message_password.setText("");
        lbl_message_email.setText("");
        lbl_message_firstName.setText("");
        lbl_message_lastName.setText("");

        String usernameError = Validation.validateTextField(username, 8); //min length is 8
        String passwordError = Validation.validateTextField(password, 8); //min length is 8
        String firstNameError = firstName.trim().isEmpty() ? "First name cannot be empty" : null;
        String lastNameError = lastName.trim().isEmpty() ? "Last name cannot be empty" : null;
        String emailError = Validation.validateEmail(email);

        boolean isValid = true;
        if (usernameError != null) {
            lbl_message_username.setText("Username: " + usernameError);
            isValid = false;
        }
        if (passwordError != null) {
            lbl_message_password.setText("Password: " + passwordError);
            isValid = false;
        }
        if (firstNameError != null) {
            lbl_message_firstName.setText(firstNameError);
            isValid = false;
        }
        if (lastNameError != null) {
            lbl_message_lastName.setText(lastNameError);
            isValid = false;
        }
        if (emailError != null) {
            lbl_message_email.setText("Email: " + emailError);
            isValid = false;
        }

        return isValid;
    }
    

    @FXML
    public void btn_signup_clicked(ActionEvent event) {
        if (validateInputCredentials()) {
            String username = tf_username.getText();
            String password = tf_password.getText();
            String firstName = tf_firstName.getText();
            String lastName = tf_lastName.getText();
            String email = tf_email.getText();

            ph = new PersistenceHandler3();
            int accountId = ph.saveRole(username, password, firstName, lastName, email, "Admin");
            if (accountId > 0) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHomePage.fxml"));
                    AnchorPane adminHomePageRoot = loader.load();

                    Scene adminHomePageScene = new Scene(adminHomePageRoot);
                    Stage currentStage = (Stage) tf_username.getScene().getWindow();
                    currentStage.setScene(adminHomePageScene);
                    currentStage.show();
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            } 
            else {
                System.out.println("account couldnt be created");
            }
        }
    }
}

