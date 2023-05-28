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
        Navigator navigator = new Navigator();
        navigator.Home(e,Controller);
    }
}
