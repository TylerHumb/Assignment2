package Assignment2;

import java.sql.*;
import java.util.HashSet;

public class UserManager {
    HashSet<User> Users = new HashSet<>();
    private User CurrentUser = null;

    String jdbcUrl = "jdbc:sqlite:users.db";

    public UserManager(){
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet users = statement.executeQuery(sql);

            while (users.next()){
                User usertoadd = new User(users.getString("Username"),users.getString("Password"),users.getString("Firstname"),users.getString("Lastname"),users.getString("StudentID"));
                if (users.getString("enrolledcourses") != null){
                    usertoadd.GetCourseManager().SetupEnrolledCourses(users.getString("enrolledcourses"));
                }
                Users.add(usertoadd);
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }

    public boolean AttemptLogin(String Username, String Password){
        for(User U: Users){
            if (U.GetUsername().equals(Username)&& U.CheckPassword(Password)){
                SetUser(U);
                return true;
            }
        }
        return false;
    }

    public HashSet<User> GetUsers() {
        return Users;
    }

    public void SetUser(User User){
        CurrentUser = User;
    }
    public User GetCurrentUser(){
        return CurrentUser;
    }

    public boolean RegisterNewUser(String Username, String Password){
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "insert into users values('"+Username+"','"+Password+"','','','');";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        Users.add(new User(Username,Password));
        return true;
    }

    public boolean DeleteUser(String Username){
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "Delete From users WHERE Username ='"+Username+"'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public boolean AddEnrolledCourse(String Coursename) {
        String sql;
        if (CurrentUser.GetCourseManager().GetEnrolledCourses().size() == 0) {
            sql = "Update users SET enrolledcourses = '" + Coursename + "' WHERE Username ='" + CurrentUser.GetUsername() + "'";
        } else {
            sql = "Update users SET enrolledcourses = '" + CurrentUser.GetCourseManager().GeneratenewEnrolled(Coursename) + "' WHERE Username ='" + CurrentUser.GetUsername() + "'";
        }
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
