module com.example.assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens Assignment2 to javafx.fxml;
    exports Assignment2;
}