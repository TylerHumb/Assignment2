package Assignment2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseViewController implements Initializable {
    @FXML
    private Button Home;
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = loader.load();
            HomeController HomeController = loader.getController(); // make an instance of the next scenes Controller to set text up
            HomeController.ObtainController(Controller); //Pass the usercontroller into the instance

            if (Controller.GetCurrentUser().GetUsername().equals("admin")) {
                HomeController.AdminMode();
            }
            if (Controller.GetCurrentUser().GetFirstname().equals("")){
                HomeController.SetWelcome("Welcome, Please finish setting up your profile by clicking edit profile!");
            }else HomeController.SetWelcome("Welcome " + Controller.GetCurrentUser().GetFullName());

            Stage stage  = (Stage) ((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); //setup the new scene
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
}
