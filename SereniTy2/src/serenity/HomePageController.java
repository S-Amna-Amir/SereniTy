package serenity;

/* Maha */

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class HomePageController {
	@FXML private ComboBox<String> intensity_box;
	private int accountId;
	private User currentAccount;

    private boolean enteredMoodToday; 
	private Stage currentStage;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	private void handleMoodButtonClick(String moodName) {
	    System.out.println("Handling mood button click: " + moodName);

	    String intensityString = intensity_box.getValue();
	    if (intensityString == null || intensityString.isEmpty()) {
	        System.out.println("Please select an intensity level.");
	        return;
	    }

	    int intensity;
	    try {
	        intensity = Integer.parseInt(intensityString);
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid intensity value: " + e.getMessage());
	        return;
	    }

	    boolean isNegative = moodName.equalsIgnoreCase("Sad") ||
	                         moodName.equalsIgnoreCase("Angry") ||
	                         moodName.equalsIgnoreCase("Anxious") ||
	                         moodName.equalsIgnoreCase("Fearful");

	    if (currentAccount instanceof User) 
	    {
	        User user = (User) currentAccount;
	        ArrayList <Exercise> exlist;
	        if(moodName.equalsIgnoreCase("Sad"))
	        {
	        	exlist = Creator.getSadexlist();
	        	
	        }
	        else if(moodName.equalsIgnoreCase("Angry"))
	        {
	        	exlist = Creator.getAngerexlist();
	        	
	        }
	        else if(moodName.equalsIgnoreCase("Fearful"))
	        {
	        	exlist = Creator.getFearexlist();
	        }
	        else if(moodName.equalsIgnoreCase("Anxious"))
	        {
	        	exlist = Creator.getAnxexlist();
	        }
	        else
	        {
	        	exlist = null;
	        }
	        user.enterMood(moodName, intensity, isNegative, exlist); 
	    } 
	    else {
	        System.out.println("Current account is not a User instance.");
	    }

	    try {
	        Stage stage = (Stage) intensity_box.getScene().getWindow();
	        loadActualHomePage(stage);
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	    System.out.println("Mood set for today: " + currentAccount.getMoodToday().getMoodName());

	}
	

    @FXML
    private void btn_happy_clicked() {
        handleMoodButtonClick("Happy");
    }

    @FXML
    private void btn_excited_clicked() {
        handleMoodButtonClick("Excited");
    }

    @FXML
    private void btn_hopeful_clicked() {
        handleMoodButtonClick("Hopeful");
    }

    @FXML
    private void btn_grateful_clicked() {
    	System.out.println("clicked grateful");
        handleMoodButtonClick("Grateful");
    }

    @FXML
    private void btn_sad_clicked() {
        handleMoodButtonClick("Sad");
    }

    @FXML
    private void btn_angry_clicked() {
        handleMoodButtonClick("Angry");
    }

    @FXML
    private void btn_anxious_clicked() {
        handleMoodButtonClick("Anxious");
    }

    @FXML
    private void btn_fearful_clicked() {
        handleMoodButtonClick("Fearful");
    }

	public void loadActualHomePage(Stage stage) throws IOException {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("ActualHomePage.fxml"));
	    AnchorPane actualHomePageRoot = loader.load();
	    ActualHomePageController actualHomePageController = loader.getController();

	    if (currentAccount instanceof User) {
	        actualHomePageController.initialize((User) currentAccount, stage);
	    }

	    Scene actualHomePageScene = new Scene(actualHomePageRoot);
	    stage.setScene(actualHomePageScene);
	    stage.show();
	}

	@FXML
    public void btn_ok_clicked() {
        System.out.println("OK button clicked.curent acc mood: ");
        try {
            Stage stage = (Stage) intensity_box.getScene().getWindow(); 
            loadActualHomePage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
	public void initialize(int accountId, Stage stage) {
	    System.out.println("HomePageController initialized with accountId: " + accountId);
	    this.setCurrentStage(stage);
	    setAccountId(accountId);
	    this.currentAccount = (User) Creator.createAccountObject(accountId);
	    if (this.currentAccount == null) {
	        System.out.println("Error: User account could not be loaded.");
	    }
	}


    @FXML
    public void initialize() {
        System.out.println("HomePageController...");
        IntStream.rangeClosed(1, 10).mapToObj(String::valueOf).forEach(intensity_box.getItems()::add);
        System.out.println("populated intensity values 1 to 10.");
    }

	public Stage getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(Stage currentStage) {
		this.currentStage = currentStage;
	}

	public boolean isEnteredMoodToday() {
		return enteredMoodToday;
	}

	public void setEnteredMoodToday(boolean enteredMoodToday) {
		this.enteredMoodToday = enteredMoodToday;
	}

}

