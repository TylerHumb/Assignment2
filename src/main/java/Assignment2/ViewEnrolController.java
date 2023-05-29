package Assignment2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewEnrolController implements Initializable {
    @FXML
    private StackPane AdvancedPy;
    @FXML
    private StackPane Algorithms;
    @FXML
    private Label Eight;
    @FXML
    private Label Eleven;
    @FXML
    private Label Fifteen;
    @FXML
    private Label Five;
    @FXML
    private Label Four;
    @FXML
    private Label Fourteen;
    @FXML
    private StackPane JavaProg;
    @FXML
    private StackPane Knowledge;
    @FXML
    private StackPane Math;
    @FXML
    private StackPane Mining;
    @FXML
    private Label Nine;
    @FXML
    private Label One;
    @FXML
    private StackPane ProgSkills;
    @FXML
    private Label Seven;
    @FXML
    private Label Six;
    @FXML
    private Label Sixteen;
    @FXML
    private Label Ten;
    @FXML
    private Label Thirteen;
    @FXML
    private Label Three;
    @FXML
    private Label Twelve;
    @FXML
    private Label Two;
    @FXML
    private GridPane gridpane;
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

    private boolean Switcher = true; // used to switch between timetable view and normal view
    UserManager Controller;
    //Below From https://www.youtube.com/watch?v=fnU1AlyuguE&ab_channel=Randomcode
    public void SetupTable(){
        ObservableList<Course> courses = FXCollections.observableArrayList();
        courses.addAll(Controller.GetCurrentUser().GetCourseManager().GetEnrolledCourses());
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

    public void Switchview(){
        if (Switcher){
            SetupTimetable();
            CourseTable.setVisible(false);
            Switcher = false;
        }else {
            CloseTimetable();
            CourseTable.setVisible(true);
            Switcher = true;
        }
    }
    public void SetupTimetable(){
        Label[] Labels = {One,Two,Three,Four,Five,Six,Seven,Eight,Nine,Ten,Eleven,Twelve,Thirteen,Fourteen,Fifteen,Sixteen};
        gridpane.setVisible(true);
        for (Label label:Labels){
            label.setVisible(true);
        }
        StackPane[] Blips = {AdvancedPy,Math,ProgSkills,JavaProg,Mining,Knowledge,Algorithms};
        for (StackPane stackPane: Blips){
            stackPane.setVisible(false);
        }
        for (Course course:Controller.GetCurrentUser().GetCourseManager().GetEnrolledCourses()){
            if (course.getCoursename().equals("Java programming")){
                JavaProg.setVisible(true);
                continue;
            }
            if (course.getCoursename().equals("Programming skills")){
                ProgSkills.setVisible(true);
                continue;
            }
            if (course.getCoursename().equals("Advanced Python programming")){
                AdvancedPy.setVisible(true);
                continue;
            }
            if (course.getCoursename().equals("Math")){
                Math.setVisible(true);
                continue;
            }
            if (course.getCoursename().equals("Data Mining")){
                Mining.setVisible(true);
                continue;
            }
            if (course.getCoursename().equals("Knowledge technologies")){
                Knowledge.setVisible(true);
                continue;
            }
            if (course.getCoursename().equals("Algorithms and complexity")){
                Algorithms.setVisible(true);
                continue;
            }
            System.out.println("Class not found?");
        }
    }
    public void CloseTimetable(){
        Label[] Labels = {One,Two,Three,Four,Five,Six,Seven,Eight,Nine,Ten,Eleven,Twelve,Thirteen,Fourteen,Fifteen,Sixteen};
        StackPane[] Blips = {AdvancedPy,Math,ProgSkills,JavaProg,Mining,Knowledge,Algorithms};
        for (StackPane stackPane: Blips){
            stackPane.setVisible(false);
        }
        for (Label label:Labels){
            label.setVisible(false);
        }
        gridpane.setVisible(false);
    }


    public void Home(ActionEvent e){
        Navigator navigator = new Navigator();
        navigator.Home(e,Controller);
    }
}
