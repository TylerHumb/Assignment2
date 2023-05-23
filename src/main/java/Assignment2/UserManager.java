package Assignment2;

import java.sql.*;
import java.util.HashSet;

public class UserManager {
    HashSet<User> Users = new HashSet<>();
    private User CurrentUser = null;

    String jdbcUrl = "jdbc:sqlite:D:\\Java projects\\Assignment2\\src\\main\\resources\\Assignment2\\users.db";

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
                Users.add(new User(users.getString("Username"),users.getString("Password"),users.getString("Firstname"),users.getString("Lastname"),users.getString("StudentID")));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }

    public boolean AttemptLogin(String Username, String Password){
        for(User U: Users){
            if (U.getUsername().equals(Username)&& U.checkpassword(Password)){
                SetUser(U);
                return true;
            }
        }
        return false;
    }

    public HashSet<User> getUsers() {
        return Users;
    }

    public void SetUser(User User){
        CurrentUser = User;
    }
    public User getCurrentUser(){
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
}
