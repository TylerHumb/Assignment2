package Assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditProfileController {
    @FXML
    private TextField NewPass;
    @FXML
    private TextField NewStud;
    @FXML
    private Label StudText;
    @FXML
    private Button StudButton;
    @FXML
    private TextField NewFirst;
    @FXML
    private TextField NewLast;
    @FXML
    private Label Warning;
    @FXML
    private Button BackButton;

    UserManager Controller;

    public void Setup(){
        if (Controller.GetCurrentUser().GetStudentnumber().equals("")){
            BackButton.setDisable(true);
            Warning.setText("Cannot Leave until Student Number is Set!");
        } else {
            StudButton.setVisible(false);
            StudText.setVisible(false);
            NewStud.setVisible(false);
        }
    }
    public void ObtainController(UserManager controller){
        Controller = controller;
    }
    public void SetStudentNumber(){
        if (NewStud.getText().equals("")){
            Warning.setText("Student Number cannot be empty!");
            return;
        }
        for (User user: Controller.GetUsers()){
            if (user.GetStudentnumber().equals(NewStud.getText())){
                Warning.setText("Student number already in use!");
                return;
            }
        }
        Controller.GetCurrentUser().setStudentnumber(NewStud.getText());
        Controller.SetStudentNum(NewStud.getText());
        Warning.setText("Student Number Successfully Set!");
        StudButton.setVisible(false);
        StudText.setVisible(false);
        NewStud.setVisible(false);
        BackButton.setDisable(false);
    }
    public void SetFirstname(){
        if (NewFirst.getText().equals("")){
            Warning.setText("New First Name cannot be empty!");
            return;
        }
        Controller.GetCurrentUser().setFirstname(NewFirst.getText());
        Controller.SetFirstName(NewFirst.getText());
        Warning.setText("First Name Successfully Set!");
    }
    public void SetLastname(){
        if (NewLast.getText().equals("")){
            Warning.setText("New Last Name cannot be empty!");
            return;
        }
        Controller.GetCurrentUser().setLastname(NewLast.getText());
        Controller.SetLastname(NewLast.getText());
        Warning.setText("Last Name Successfully Set!");
    }
    public void SetPassword(){
        if (NewPass.getText().equals("")){
            Warning.setText("New Password cannot be empty!");
            return;
        }
        Controller.GetCurrentUser().setPassword(NewPass.getText());
        Controller.SetPassword(NewPass.getText());
        Warning.setText("Password Successfully Set!");
    }
    public void Home(ActionEvent e){
        Navigator navigator = new Navigator();
        navigator.Home(e,Controller);
    }
    public void Delete(ActionEvent e){
        Controller.DeleteUser(Controller.GetCurrentUser().GetUsername());
        Navigator navigator = new Navigator();
        navigator.Login(e,Controller);
    }
}
