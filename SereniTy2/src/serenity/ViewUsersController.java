package serenity;
/* Maha */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;
import java.util.List;

import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewUsersController {
    @FXML private TableView<User> tableView;
    @FXML private TableColumn<User, Integer> colAccountId;
    @FXML private TableColumn<User, String> colUsername;
    @FXML private TableColumn<User, String> colFullName;
    @FXML private TableColumn<User, String> colEmail;
    @FXML private TableColumn<User, Float> colAge; 
    @FXML private TableColumn<User, String> colRegDate;

    private Admin admin;

    @FXML
    public void initialize() {
        colAccountId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getAccountId()).asObject());
        colUsername.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUsername()));
        colFullName.setCellValueFactory(data -> {
            User user = data.getValue();
            return new SimpleStringProperty(user.getFirstName() + " " + user.getLastName());
        });
        colEmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        colAge.setCellValueFactory(data -> new SimpleFloatProperty(data.getValue().getAge()).asObject());

        
    }
    
    public void setAdmin(Admin admin) {
        this.admin = admin;
        loadUserAccounts();
    }


    private void loadUserAccounts() {
        List<User> users = admin.viewAccounts();
        ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
        tableView.setItems(observableUsers);
    }
 
    
}

