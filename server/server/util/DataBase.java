package util;

import java.util.ArrayList;
import exception.*;
import model.Course; 


class DataBase {

// in-memory database
// holds list of Courses and can perform ADD, DELETE, SHOW actions

    private static ArrayList<Course> courses = new ArrayList<Course>(); 

    public static void add(Course course) {
        courses.add(course);
    }

    public static void delete(int idx) {
        if (idx < 0 || idx >= courses.size())
            throw new OutOfBoundException();
        courses.remove(idx);
    }

    public static String show() {
        int idx = 0, sum_weighted_grades = 0, sum_units = 0;
        String result = new String();
        for (Course course : courses) {
            // needed to calculate average
            sum_weighted_grades += course.getGrade() * course.getUnit();
            sum_units += course.getUnit();

            // idx: name, unit, grade
            result += idx + ": " + course.getName() + ", "
                + course.getUnit() + ", " + course.getGrade() + "\n";
            
            idx++;
        }

        // calculate average
        float avg = sum_weighted_grades / sum_units;
        // append to result
        result += "avg: " + avg + "\n";

        return result;
    }
}
