package portal.teacher_student_portal;

import java.util.*;

public class Course {
    String course_name;
    String course_id;
    String teacher_id;
    List<Student> students;

    Course(String course_name, String course_id, String teacher_id){
        this.course_name = course_name;
        this.course_id = course_id;
        this.teacher_id = teacher_id;
        students = new ArrayList<>();
    }
}
