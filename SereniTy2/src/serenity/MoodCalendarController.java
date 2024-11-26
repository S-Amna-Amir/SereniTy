package serenity;
/* Maha */

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class MoodCalendarController {
    @FXML private TableView<Mood2> tableView; 
    @FXML private TableColumn<Mood2, String> colDate;
    @FXML private TableColumn<Mood2, String> colMood;
    @FXML private TableColumn<Mood2, Integer> colIntensity; 
    private User loggedInUser; 

    @FXML
    public void initialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        colDate.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getDate().format(formatter)));
        colMood.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getMoodName()));
        colIntensity.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getIntensity()));
        if (loggedInUser != null) {
            loadMoodData();
        }
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
        loadMoodData();
    }

    private void loadMoodData() {
        List<Mood2> moods = loggedInUser.viewMoods();
        ObservableList<Mood2> observableMoods = FXCollections.observableArrayList(moods);
        tableView.setItems(observableMoods);
    }
}

