package Assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomeController {
    @FXML
    private Text Welcome;
    @FXML
    private TextField ToDelete;
    @FXML
    private Button Delete;

    @FXML
    private Label Confirmation;

    private UserManager Controller;
    private Navigator navigator = new Navigator();

    public void SetWelcome(String message){
        Welcome.setText(message);
    }

    public void ObtainController(UserManager controller){
        this.Controller = controller;
    }

    public void Login(ActionEvent e) throws IOException {
        navigator.Login(e,Controller);
    }
    public void CourseView(ActionEvent e) throws IOException{
        navigator.CourseView(e,Controller);
    }
    public void AdminMode(){
        SetWelcome("Hey Tyler");
        ToDelete.setVisible(true);
        Delete.setVisible(true);
        Confirmation.setVisible(true);
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
    public void DeleteUser(){
        if (Controller.DeleteUser(ToDelete.getText())){
            Confirmation.setText("User: " + ToDelete.getText() + " Successfully deleted");
        }else Confirmation.setText("Failed to delete user");
    }
}
