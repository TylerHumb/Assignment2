package Assignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class CourseManager {
    private HashSet<Course> AvailableCourses = new HashSet<>();
    private HashSet<Course> EnrolledCourses = new HashSet<>();

    public CourseManager() {
        BufferedReader br;
        String fileName = "course.csv";
        String line;
        {
            try {
                br = new BufferedReader(new FileReader(fileName));
                br.readLine();
                while ((line = br.readLine()) != null){
                    String[] values = line.split(","); // split the csv data into an array and make the courselist out of it
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

    public HashSet<Course> GetAvailableCourses(){
        return AvailableCourses;
    }
    public HashSet<Course> GetEnrolledCourses(){
        return EnrolledCourses;
    }
    public boolean AddEnrolledCourse(Course course){
        return EnrolledCourses.add(course);
    }
    public boolean RemoveCourse(Course course){
        return EnrolledCourses.remove(course);
    }

    public void SetupEnrolledCourses(String Coursenames){ // when getting userdata from the JDBC we need to convert the list of course names into actual courses
        String[] Coursestoadd = Coursenames.split(",");
        for (String coursename:Coursestoadd){
            for (Course course:AvailableCourses){ // so this function finds the corresponding course for each course name and adds it to the users enrolled course
                if (course.getCoursename().equals(coursename)){
                    AddEnrolledCourse(course);
                }
            }
        }
    }
    //Generates a list of course names to be written to the database so it can be read later, this function adds a new coursename ot the list
    public String GeneratenewEnrolled(String Coursetoadd){
        String names = "";
        for (Course course:EnrolledCourses){
            names = names + course.getCoursename() + ",";
        }
            names = names + Coursetoadd;
        return names;
    }
    //Whilst this function generates the list without adding a new coursename to it, this is used for after unenrolling
    public String GeneratenewEnrolled(){
        String names = "";
        for (Course course:EnrolledCourses){
            names = names + course.getCoursename() + ",";
        }
        if (names.length() != 0) {
            names = names.substring(0, names.length() - 1);
        }
        return names;
    }
}