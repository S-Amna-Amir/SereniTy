package serenity;

/* Maha */

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminHomePageController {
    private Admin admin; 
    private Stage currentStage; 
    
	public void initializeAdminDashboard(Admin admin) {
	    this.admin = admin;
	    System.out.println("admin initialized for " + admin.getUsername());
	}
	
	@FXML
	public void btn_add_user_clicked() {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUserPage.fxml"));
	        AnchorPane root = loader.load();

	        AddUserController controller = loader.getController();
	        controller.initialize(admin); 

	        currentStage.setScene(new Scene(root));
	        currentStage.setTitle("Add User");
	        currentStage.show();

	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	@FXML
	public void btn_delete_user_clicked() {
	    System.out.println("button clicked!");
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteUserPage.fxml"));
	        AnchorPane root = loader.load();

	        DeleteUserController controller = loader.getController();
	        controller.initialize(admin); 

	        currentStage.setScene(new Scene(root));
	        currentStage.setTitle("Delete User");
	        currentStage.show();

	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	    System.out.println("delete button func executed");
	}
	
	@FXML
	private void btn_view_accounts_clicked() {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAccounts.fxml"));
	        AnchorPane pane = loader.load();

	        ViewUsersController controller = loader.getController();
	        controller.setAdmin(admin); 

	        currentStage.setScene(new Scene(pane));
	        currentStage.setTitle("View Accounts");
	        currentStage.show();

	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@FXML
	private void btn_edit_user_account_clicked() {
	    System.out.println("edit clicked");
	    try {
	        System.out.println("try");
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditAccout.fxml")); 
	        AnchorPane pane = loader.load();

	        System.out.println("right");
	        EditUserAccountController controller = loader.getController();
	        controller.initialize(admin); 
	        
	        currentStage.setScene(new Scene(pane));
	        currentStage.setTitle("Edit User Account");
	        currentStage.show();
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}










	
	public void initializeHomePage(int accountId) {
		
	}

	public void initialize(Admin admin, Stage stage) {
	    this.admin = admin;
	    this.currentStage = stage;
	    System.out.println("admin initialized for: " + admin.getUsername());
	}

	public Stage getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(Stage currentStage) {
		this.currentStage = currentStage;
	}

}
