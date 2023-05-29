package Assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class HomeController {
    @FXML
    private Text Welcome;
    @FXML
    private Label Studnum;

    private UserManager Controller;
    private final Navigator navigator = new Navigator();

    public void SetWelcome(String message){
        Welcome.setText(message);
        Studnum.setText("Student: "+ Controller.GetCurrentUser().GetStudentnumber());
    }

    public void ObtainController(UserManager controller){
        this.Controller = controller;
    }

    public void Login(ActionEvent e){
        navigator.Login(e,Controller);
    }
    public void CourseView(ActionEvent e){
        navigator.CourseView(e,Controller);
    }
    public void ViewEnrol(ActionEvent e){
        navigator.ViewEnrol(e,Controller);
    }
    public void EnrolView(ActionEvent e){
        navigator.EnrolView(e,Controller);
    }
    public void UnenrolView(ActionEvent e){
        navigator.UnenrolView(e,Controller);
    }
    public void EditProfile(ActionEvent e){
        navigator.EditProfile(e,Controller);
    }
    public void Export(ActionEvent e){
        navigator.ExportView(e,Controller);
    }
}
