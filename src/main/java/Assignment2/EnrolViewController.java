package Assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class EnrolViewController {
    @FXML
    private ListView<Course> PossibleEnrol;
    @FXML
    private Label Warning;
    @FXML
    private TextField Search;

    private List<Course> Courses = new ArrayList<>();

    private UserManager Controller;
    public void ObtainController(UserManager controller){
        this.Controller = controller;
    }

    public void PopulateList(){
        PossibleEnrol.getItems().clear(); // clears the list before adding new courses to it
        Courses.clear();
        for(Course course:Controller.GetCurrentUser().GetCourseManager().GetAvailableCourses()){
            if (!Controller.GetCurrentUser().GetCourseManager().GetEnrolledCourses().contains(course)){ // if the course isn't in the users enrolled courses
                Courses.add(course); // add it to the list of courses to enrol in
            }
        }
        PossibleEnrol.getItems().setAll(Courses);
    }
    public void PopulateListSearch(){
        PossibleEnrol.getItems().clear();
        Courses.clear();
        for(Course course:Controller.GetCurrentUser().GetCourseManager().GetAvailableCourses()){
            if (!Search.getText().equals("")) {
                if (!Controller.GetCurrentUser().GetCourseManager().GetEnrolledCourses().contains(course) && course.getCoursename().toLowerCase().contains(Search.getText().toLowerCase())) {
                    Courses.add(course); // add it to the list of courses to enrol in
                }
            } else PopulateList(); // if the search bar is empty just run a normal populate list
        }
        PossibleEnrol.getItems().setAll(Courses);
    }

    public void Enrol(){
        if (PossibleEnrol.getSelectionModel().getSelectedItem() == null){ //  ensures the user has clicked on a course
            Warning.setText("Please select a course!");
        }
        else if (Controller.AddEnrolledCourse(PossibleEnrol.getSelectionModel().getSelectedItem().getCoursename())){
            Warning.setText("Successfully enrolled in course!");
            PopulateList();
        }else {
            Warning.setText("Error whilst enrolling in course!");
            PopulateList(); // if the course cannot be enrolled for whatever reason
        }
    }
    public void Home(ActionEvent e){
        Navigator navigator = new Navigator();
        navigator.Home(e,Controller);
    }
}
