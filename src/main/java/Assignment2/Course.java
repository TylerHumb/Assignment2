package Assignment2;

public class Course {
    private String Coursename;
    private String Capacity;
    private String Year;
    private String Delivery;
    private String Day;
    private String Time;
    private String Duration;

    public Course(String coursename, String capacity, String year, String delivery, String day, String time, String duration) {
        Coursename = coursename;
        Capacity = capacity;
        Year = year;
        Delivery = delivery;
        Day = day;
        Time = time;
        Duration = duration;
    }

    public String getCoursename() {
        return Coursename;
    }

    @Override
    public String toString() {
        return Coursename + " - " + Capacity + " - " + Year + " - " + Delivery + " - " + Day + " - " + Time + " - " + Duration;
    }
}