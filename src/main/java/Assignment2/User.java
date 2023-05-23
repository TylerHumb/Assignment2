package Assignment2;

public class User {
    private String Username;
    private String Password;
    private String Firstname;
    private String Lastname;
    private String Studentnumber;
    CourseManager EnrolledCourses = new CourseManager();

    public User(String Username,String Password, String Firstname, String Lastname,String Studentnumber){
        this.Username = Username;
        this.Password = Password;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.Studentnumber = Studentnumber;
    }
    public User(String Username, String Password){
        this.Username = Username;
        this.Password = Password;
        this.Firstname = "";
        this.Lastname = "";
        this.Studentnumber = "";
    }
    //since we dont want to return the password for privacy reasons
    public boolean checkpassword(String Pass){
        return Password.equals(Pass);
    }

    public String getUsername() {
        return Username;
    }
    public String getFullName(){
        return Firstname + " " + Lastname;
    }

    public String getFirstname() {
        return Firstname;
    }

    public CourseManager getCourseManager() {
        return EnrolledCourses;
    }
}
