package serenity;
/* Maha */

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ActualHomePageController {
	private User loggedInUser;
	private Stage currentStage;
	 private Stage stage;
	    private Scene scene;
	    private Parent root;
	    
	    @FXML
		public void switchToHighlights(ActionEvent event) throws IOException
		{
			root = FXMLLoader.load(getClass().getResource("Higlights.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	    
	    @FXML
	    public void switchToJEntry(ActionEvent event) throws IOException
		{
			root = FXMLLoader.load(getClass().getResource("JEntry.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	    
	    @FXML
	    public void switchToJournal(ActionEvent event) throws IOException
		{
			root = FXMLLoader.load(getClass().getResource("Journal.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	    
	    @FXML
	    public void switchToExercises(ActionEvent event) throws IOException
		{
			root = FXMLLoader.load(getClass().getResource("Exercises.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}


	public void initialize(User user, Stage stage) {
	    this.loggedInUser = user;
	    this.currentStage = stage;

	    Mood2 mood = loggedInUser.getMoodToday();

	    if (mood == null) {
	        System.out.println("No mood entered for today. Redirecting to mood page.");
	        go_to_mood_page(stage);
	    } 
	    else {
	        System.out.println("Mood already entered for today.");
	        Alert moodAlert = new Alert(AlertType.INFORMATION);
	        moodAlert.setTitle("Mood Entry");
	        moodAlert.setHeaderText("Mood Logged");
	        moodAlert.setContentText("Your mood has been logged for today");
	        moodAlert.showAndWait();
	    }
	}
	@FXML
	public void btn_mood_calendar_clicked() {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("MoodCalendarPage.fxml"));
	        AnchorPane moodCalendarRoot = loader.load();

	        MoodCalendarController moodCalendarController = loader.getController();

	        moodCalendarController.setLoggedInUser(loggedInUser);

	        Scene moodCalendarScene = new Scene(moodCalendarRoot);
	        currentStage.setScene(moodCalendarScene);
	        currentStage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@FXML
	public void btn_delete_account_clicked() {
		System.out.println("Dlete clicked.");
		boolean deletionSuccess = loggedInUser.deleteAccount();
		if (deletionSuccess) {
			System.out.println("Account deleted");
			Alert successAlert = new Alert(AlertType.INFORMATION);
			successAlert.setTitle("Success");
			successAlert.setHeaderText("Account Deletion Successful");
			successAlert.setContentText("Your account has been deleted successfully.");
			successAlert.showAndWait();

			go_to_start_screen();
		} else {
			System.out.println("Deletion failed");
			Alert failureAlert = new Alert(AlertType.ERROR);
			failureAlert.setTitle("Error");
			failureAlert.setHeaderText("Account Deletion Failed");
			failureAlert.setContentText("An error occurred while deleting your account.");
			failureAlert.showAndWait();
		}
	}

	@FXML
	public void btn_edit_account_clicked() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EditMyAccount.fxml"));
		AnchorPane editAccountRoot = loader.load();

		EditMyAccountController editAccountController = loader.getController();
		editAccountController.initialize(loggedInUser, currentStage);

		Scene editAccountScene = new Scene(editAccountRoot);
		currentStage.setScene(editAccountScene);
		currentStage.show();
		System.out.println("in edit account");
	}

	
	public void go_to_start_screen() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
			AnchorPane sampleRoot = loader.load();
			Scene sampleScene = new Scene(sampleRoot);

			currentStage.setScene(sampleScene);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void go_to_mood_page(Stage stage) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
	        AnchorPane moodPageRoot = loader.load();

	        HomePageController moodController = loader.getController();
	        moodController.initialize(loggedInUser.getAccountId(), currentStage);  
	        
	        stage.setScene(new Scene(moodPageRoot));  
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}




}

