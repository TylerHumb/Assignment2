package Assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ExportController {
    @FXML
    private TextField Filename;
    @FXML
    private Label Warning;
    @FXML
    private Label Path;
    private UserManager Controller;


    public void ObtainController(UserManager controller){
        this.Controller = controller;
    }
    public void Export(){ // allows the user to name the file whatever they want
        if (Filename.getText().equals("")){
            Warning.setText("File name cannot be empty!");
            return;
        }
        File newfile = new File(Filename.getText() + ".txt");
        PrintWriter pw;
        try {
            if (newfile.createNewFile()){
                pw = new PrintWriter(new FileWriter(newfile,true));
                for (Course course: Controller.GetCurrentUser().GetCourseManager().GetEnrolledCourses()){
                    pw.append(course.toString()).append("\n");
                } // writes every course down
                pw.close();
                Warning.setText("File has been successfuly written!");
                Path.setText("File can be found at: "+newfile.getAbsolutePath()); // prints the filepath so the user knows where it is saved
            }else {
                Warning.setText("An error has occured during filewriting!");
            }
        }catch (IOException e){
            Warning.setText("An error has occured during filewriting!");
        }
    }
    public void Home(ActionEvent e){
        Navigator navigator = new Navigator();
        navigator.Home(e,Controller);
    }
}
