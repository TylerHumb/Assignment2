package Assignment2;

public class Course {
    private String Coursename;
    private String Capacity;
    private String Year;
    private String Delivery;
    private String Day;
    private String Time;
    private String Duration;

    boolean enrolled;

    public Course(String coursename, String capacity, String year, String delivery, String day, String time, String duration) {
        Coursename = coursename;
        Capacity = capacity;
        Year = year;
        Delivery = delivery;
        Day = day;
        Time = time;
        Duration = duration;
        enrolled = false;
    }

    public String getCoursename() {
        return Coursename;
    }

    public String getCapacity() {
        return Capacity;
    }

    public String getYear() {
        return Year;
    }

    public String getDelivery() {
        return Delivery;
    }

    public String getDay() {
        return Day;
    }

    public String getTime() {
        return Time;
    }

    public String getDuration() {
        return Duration;
    }
}