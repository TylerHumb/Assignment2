package Assignment2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseViewController implements Initializable {
    @FXML
    private TableView<Course> CourseTable;
    @FXML
    private TableColumn<Course, String> CourseNameCol;
    @FXML
    private TableColumn<Course, String> CapacityCol;
    @FXML
    private TableColumn<Course, String> YearCol;
    @FXML
    private TableColumn<Course, String> DeliveryCol;
    @FXML
    private TableColumn<Course, String> DayCol;
    @FXML
    private TableColumn<Course, String> TimeCol;
    @FXML
    private TableColumn<Course, String> DurationCol;
    UserManager Controller;
    //Below From https://www.youtube.com/watch?v=fnU1AlyuguE&ab_channel=Randomcode
    public void SetupTable(){
        ObservableList<Course> courses = FXCollections.observableArrayList();

        courses.addAll(Controller.GetCurrentUser().GetCourseManager().GetAvailableCourses());
        CourseTable.setItems(courses);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        CourseNameCol.setCellValueFactory(new PropertyValueFactory<>("Coursename"));
        CapacityCol.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
        YearCol.setCellValueFactory(new PropertyValueFactory<>("Year"));
        DeliveryCol.setCellValueFactory(new PropertyValueFactory<>("Delivery"));
        DayCol.setCellValueFactory(new PropertyValueFactory<>("Day"));
        TimeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
        DurationCol.setCellValueFactory(new PropertyValueFactory<>("Duration"));
    }

    public void ObtainController(UserManager controller){
        this.Controller = controller;
    }

    public void Home(ActionEvent e){
        Navigator navigator = new Navigator();
        navigator.Home(e,Controller);
    }
}
