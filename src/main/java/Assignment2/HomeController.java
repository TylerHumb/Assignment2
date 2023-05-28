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
    private Button EditProfile;
    @FXML
    private Button Delete;

    @FXML
    private Label Confirmation;
    private Stage stage;

    private UserManager Controller;

    public void SetWelcome(String message){
        Welcome.setText(message);
    }

    public void ObtainController(UserManager controller){
        this.Controller = controller;
    }

    public void Login(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        stage  = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Controller.SetUser(null);
        stage.show();
    }
    public void CourseView(ActionEvent e) throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseView.fxml"));
            Parent root = loader.load();
            CourseViewController courseViewController = loader.getController(); // make an instance of the next scenes Controller to set text up
            courseViewController.ObtainController(Controller); //Pass the usercontroller into the instance

            courseViewController.SetupTable();

            stage  = (Stage) ((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); //setup the new scene
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
    public void AdminMode(){
        SetWelcome("Hey Tyler");
        ToDelete.setVisible(true);
        EditProfile.setVisible(false);
        Delete.setVisible(true);
        Confirmation.setVisible(true);
    }
    public void EnrolView(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EnrolView.fxml"));
            Parent root = loader.load();
            EnrolViewController enrolViewController = loader.getController(); // make an instance of the next scenes Controller to set text up
            enrolViewController.ObtainController(Controller); //Pass the usercontroller into the instance
            enrolViewController.PopulateList();
            stage  = (Stage) ((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); //setup the new scene
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
    public void DeleteUser(){
        if (Controller.DeleteUser(ToDelete.getText())){
            Confirmation.setText("User: " + ToDelete.getText() + " Successfully deleted");
        }else Confirmation.setText("Failed to delete user");
    }
}
