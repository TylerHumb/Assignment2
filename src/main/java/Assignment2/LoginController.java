package Assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private PasswordField Password;
    @FXML
    private TextField Username;
    @FXML
    private Label Error;
    @FXML
    private Button Login;
    @FXML
    private Button Register;
    @FXML
    private Button Back;
    @FXML
    private  Button AttemptReg;
    @FXML
    private Label Help;
    private Stage stage;
    UserManager Controller = new UserManager();


    public void AttemptLogin(ActionEvent e){
        if (Controller.AttemptLogin(Username.getText(),Password.getText())){
            Navigator navigator = new Navigator();
            navigator.Home(e,Controller);
        } else Error.setText("Invalid Username or Password!");

    }
    public void RegisterNew(){
        Register.setVisible(false);
        Login.setVisible(false);
        Back.setVisible(true);
        AttemptReg.setVisible(true);
        Help.setVisible(true);
    }
    public void Back(){
        Register.setVisible(true);
        Login.setVisible(true);
        Back.setVisible(false);
        AttemptReg.setVisible(false);
        Help.setVisible(false);
    }
    public boolean AttemptRegister(){
        for(User U: Controller.GetUsers()){
            if (U.GetUsername().equals(Username.getText())){
                Error.setText("Username Already taken!");
                return false;
            }
        }
        if (Username.getText().equals("") && Password.getText().equals("")){
            Error.setText("Please fill out required fields!");
            return false;
        }
        if (Username.getText().length() > 20){
            Error.setText("Usernames must be under 20 characters");
            return false;
        }
        if (Username.getText().equals("")){
            Error.setText("Please enter a username");
            return false;
        }
        if (Password.getText().equals("")){
            Error.setText("Please enter a password");
            return false;
        }
        return  Controller.RegisterNewUser(Username.getText(),Password.getText());

    }



}
