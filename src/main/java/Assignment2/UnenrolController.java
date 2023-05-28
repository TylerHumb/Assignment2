package Assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class UnenrolController {
    @FXML
    private ListView<Course> PossibleUnenrol;
    @FXML
    private Label Warning;

    private UserManager Controller;


    public void ObtainController(UserManager controller){
        this.Controller = controller;
    }

    public void PopulateList(){
        PossibleUnenrol.getItems().setAll(Controller.GetCurrentUser().GetCourseManager().GetEnrolledCourses());
    }

    public void Unenrol(){
        if (PossibleUnenrol.getSelectionModel().getSelectedItem() == null){
            Warning.setText("Please select a course!");
        }
        else if (Controller.RemoveEnrolledCourse(PossibleUnenrol.getSelectionModel().getSelectedItem().getCoursename())){
            Warning.setText("Successfully Unenrolled from course!");
            PossibleUnenrol.getItems().clear();
            PopulateList();
        }else {
            Warning.setText("Error whilst Unenrolling from course!");
            PossibleUnenrol.getItems().clear();
            PopulateList();
        }
    }
    public void Home(ActionEvent e){
        Navigator navigator = new Navigator();
        navigator.Home(e,Controller);
    }
}
