package serenity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class S1controller {

    @FXML
    public void goToMenu(ActionEvent event) {
        try {
            // Load the Goals.fxml
            AnchorPane goalsForm = FXMLLoader.load(getClass().getResource("Goals.fxml"));

            // Create a new scene with the loaded goals form
            Scene scene = new Scene(goalsForm, 394, 392);

            // Get the current stage (window)
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
