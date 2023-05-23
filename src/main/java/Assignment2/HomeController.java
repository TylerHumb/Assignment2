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
    private Text welcome;
    @FXML
    private TextField Todelete;
    @FXML
    private Button editprofile;
    @FXML
    private Button Delete;

    @FXML
    private Label confirmation;
    private Stage stage;

    private UserManager controller;

    public void setWelcome(String message){
        welcome.setText(message);
    }

    public void obtaincontroller(UserManager controller){
        this.controller = controller;
    }

    public void login(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        stage  = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        controller.SetUser(null);
        stage.show();
    }
    public void CourseView(ActionEvent e) throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseView.fxml"));
            Parent root = loader.load();
            CourseViewController courseViewController = loader.getController(); // make an instance of the next scenes controller to set text up
            courseViewController.obtaincontroller(controller); //Pass the usercontroller into the instance

            courseViewController.setuptable();

            stage  = (Stage) ((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); //setup the new scene
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
    public void adminmode(){
        setWelcome("Hey Tyler");
        Todelete.setVisible(true);
        editprofile.setVisible(false);
        Delete.setVisible(true);
        confirmation.setVisible(true);
    }
    public void DeleteUser(){
        if (controller.DeleteUser(Todelete.getText())){
            confirmation.setText("User:" + Todelete.getText() + "Successfully deleted");
        }else confirmation.setText("Failed to delete user");
    }
}
