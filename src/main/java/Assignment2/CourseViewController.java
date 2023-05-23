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
import java.sql.Time;
import java.util.ResourceBundle;

public class CourseViewController implements Initializable {
    @FXML
    private Button Home;
    @FXML
    private TableView<Course> CourseTable;
    @FXML
    private TableColumn<Course, String> Coursenamecol;
    @FXML
    private TableColumn<Course, String> Capacitycol;
    @FXML
    private TableColumn<Course, String> Yearcol;
    @FXML
    private TableColumn<Course, String> Deliverycol;
    @FXML
    private TableColumn<Course, String> Daycol;
    @FXML
    private TableColumn<Course, String> Timecol;
    @FXML
    private TableColumn<Course, String> Durationcol;
    UserManager controller;
    //Below From https://www.youtube.com/watch?v=fnU1AlyuguE&ab_channel=Randomcode
    public void setuptable(){
        ObservableList<Course> courses = FXCollections.observableArrayList();

        courses.addAll(controller.getCurrentUser().getCourseManager().getAvailableCourses());
        CourseTable.setItems(courses);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Coursenamecol.setCellValueFactory(new PropertyValueFactory<>("Coursename"));
        Capacitycol.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
        Yearcol.setCellValueFactory(new PropertyValueFactory<>("Year"));
        Deliverycol.setCellValueFactory(new PropertyValueFactory<>("Delivery"));
        Daycol.setCellValueFactory(new PropertyValueFactory<>("Day"));
        Timecol.setCellValueFactory(new PropertyValueFactory<>("Time"));
        Durationcol.setCellValueFactory(new PropertyValueFactory<>("Duration"));
    }

    public void obtaincontroller(UserManager controller){
        this.controller = controller;
    }

    public void Home(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = loader.load();
            HomeController HomeController = loader.getController(); // make an instance of the next scenes controller to set text up
            HomeController.obtaincontroller(controller); //Pass the usercontroller into the instance

            if (controller.getCurrentUser().getUsername().equals("admin")) {
                HomeController.adminmode();
            }
            if (controller.getCurrentUser().getFirstname().equals("")){
                HomeController.setWelcome("Welcome, Please finish setting up your profile by clicking edit profile!");
            }else HomeController.setWelcome("Welcome " + controller.getCurrentUser().getFullName());

            Stage stage  = (Stage) ((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); //setup the new scene
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
}
