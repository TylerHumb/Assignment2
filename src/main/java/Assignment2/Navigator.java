package Assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigator { // solely for switching views to reduce re-used code
    private Stage stage;

    private UserManager Controller;

    public void Login(ActionEvent e, UserManager Controller) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        stage  = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Controller.SetUser(null);
        stage.show();
    }
    public void CourseView(ActionEvent e,UserManager Controller) throws IOException{
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
    public void EnrolView(ActionEvent e,UserManager Controller){
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
    public void UnenrolView(ActionEvent e,UserManager Controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UnenrolView.fxml"));
            Parent root = loader.load();
            UnenrolController unenrolController = loader.getController(); // make an instance of the next scenes Controller to set text up
            unenrolController.ObtainController(Controller); //Pass the usercontroller into the instance
            unenrolController.PopulateList();
            stage  = (Stage) ((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); //setup the new scene
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
    public void Home(ActionEvent e,UserManager Controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = loader.load();
            HomeController HomeController = loader.getController(); // make an instance of the next scenes Controller to set text up
            HomeController.ObtainController(Controller); //Pass the usercontroller into the instance

            if (Controller.GetCurrentUser().GetUsername().equals("admin")) {
                HomeController.AdminMode();
            }
            if (Controller.GetCurrentUser().GetFirstname().equals("")){
                HomeController.SetWelcome("Welcome, Please finish setting up your profile by clicking edit profile!");
            }else HomeController.SetWelcome("Welcome " + Controller.GetCurrentUser().GetFullName());

            Stage stage  = (Stage) ((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); //setup the new scene
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
}
