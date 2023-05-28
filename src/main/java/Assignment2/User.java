package Assignment2;

public class User {
    private final String Username;
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
    //since we don't want to return the password for privacy reasons
    public boolean CheckPassword(String Pass){
        return Password.equals(Pass);
    }

    public String GetUsername() {
        return Username;
    }
    public String GetFullName(){
        return Firstname + " " + Lastname;
    }

    public String GetFirstname() {
        return Firstname;
    }

    public String GetStudentnumber(){return Studentnumber;}

    public CourseManager GetCourseManager() {
        return EnrolledCourses;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public void setStudentnumber(String studentnumber) {
        Studentnumber = studentnumber;
    }
}
