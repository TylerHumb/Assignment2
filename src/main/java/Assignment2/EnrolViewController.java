package Assignment2;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnrolViewController {
    @FXML
    private ListView<Course> PossibleEnrol;
    @FXML
    private Label Warning;

    private List<Course> Courses = new ArrayList<Course>();

    private UserManager Controller;


    public void ObtainController(UserManager controller){
        this.Controller = controller;
    }

    public void PopulateList(){
        for(Course course:Controller.GetCurrentUser().GetCourseManager().GetAvailableCourses()){
            if (!Controller.GetCurrentUser().GetCourseManager().GetEnrolledCourses().contains(course)){ // if the course isn't in the users enrolled courses
                Courses.add(course); // add it to the list of courses to enrol in
            }
        }
        PossibleEnrol.getItems().setAll(Courses);
    }

    public void Enrol(){
        if (PossibleEnrol.getSelectionModel().getSelectedItem() == null){
            Warning.setText("Please select a course!");
        }
        else if (Controller.AddEnrolledCourse(PossibleEnrol.getSelectionModel().getSelectedItem().getCoursename())){
            Controller.GetCurrentUser().GetCourseManager().AddEnrolledCourse(PossibleEnrol.getSelectionModel().getSelectedItem());
            Warning.setText("Successfully enrolled in course!");
            PossibleEnrol.getItems().clear();
            Courses.clear();
            PopulateList();
        }else {
            Warning.setText("Error whilst enrolling in course!");
            PossibleEnrol.getItems().clear();
            Courses.clear();
            PopulateList();
        }
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
