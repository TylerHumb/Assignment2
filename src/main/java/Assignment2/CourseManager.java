package Assignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class CourseManager {
    private HashSet<Course> AvailableCourses = new HashSet<Course>();
    private HashSet<Course> EnrolledCourses = new HashSet<Course>();

    public CourseManager() {
        BufferedReader br;
        String fileName = "D:\\Java projects\\Assignment2\\src\\main\\resources\\Assignment2\\course.csv";
        String line = "";
        {
            try {
                br = new BufferedReader(new FileReader(fileName));
                br.readLine();
                while ((line = br.readLine()) != null){
                    String[] values = line.split(",");
                    AvailableCourses.add(new Course(values[0],values[1],values[2],values[3],values[4],values[5],values[6]));
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e){
                System.out.println("Error whilst reading file");
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Error in file format, please check file");
            }
        }
    }

    public HashSet<Course> getAvailableCourses(){
        return AvailableCourses;
    }
    public HashSet<Course> getEnrolledCourses(){
        return EnrolledCourses;
    }
    public boolean addEnrolledCourse(Course course){
        return EnrolledCourses.add(course);
    }
    public boolean Removecourse(Course course){
        return EnrolledCourses.remove(course);
    }
}