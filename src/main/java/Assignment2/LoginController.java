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
import java.util.Objects;

public class LoginController {
    @FXML
    private PasswordField Password;
    @FXML
    private TextField Username;
    @FXML
    private Label error;
    @FXML
    private Button login;
    @FXML
    private Button register;
    @FXML
    private Button back;
    @FXML
    private  Button attemptreg;
    @FXML
    private Label help;
    private Stage stage;
    UserManager controller = new UserManager();


    public void AttemptLogin(ActionEvent e){
        if (controller.AttemptLogin(Username.getText(),Password.getText())){
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

                stage  = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show(); //setup the new scene
            }catch (IOException exception){
                System.out.println(exception.getMessage());
            }

        } else error.setText("Invalid Username or Password!");

    }
    public void registernew(){
        register.setVisible(false);
        login.setVisible(false);
        back.setVisible(true);
        attemptreg.setVisible(true);
        help.setVisible(true);
    }
    public void back(){
        register.setVisible(true);
        login.setVisible(true);
        back.setVisible(false);
        attemptreg.setVisible(false);
        help.setVisible(false);
    }
    public boolean attemptregister(){
        for(User U:controller.getUsers()){
            if (U.getUsername().equals(Username.getText())){
                error.setText("Username Already taken!");
                return false;
            }
        }
        if (Username.getText().equals("") && Password.getText().equals("")){
            error.setText("Please fill out required fields!");
            return false;
        }
        if (Username.getText().length() > 20){
            error.setText("Usernames must be under 20 characters");
            return false;
        }
        if (Username.getText().equals("")){
            error.setText("Please enter a username");
            return false;
        }
        if (Password.getText().equals("")){
            error.setText("Please enter a password");
            return false;
        }
        return  controller.RegisterNewUser(Username.getText(),Password.getText());

    }



}
