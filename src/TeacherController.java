import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TeacherController {
Scanner input = new Scanner(System.in);
    public void teacherViewAvailableCourses(HashMap<String , ArrayList<String>> teacherCoursesHashMap, HashMap<String , Course> courseHashMap , Teacher teacher ){
        ArrayList<Course> result = new ArrayList<>(courseHashMap.values());


        ArrayList<String> courses = teacherCoursesHashMap.get(teacher.getId());


        for (Course course : result) {

            if (courses == null) {
                if (course.getSpecialization().equals(teacher.getSpecialization())) {

                    System.out.println(course);

                }
            } else if (!(courses.contains(course.getId()))) {
                if (course.getSpecialization().equals(teacher.getSpecialization())) {

                    System.out.println(course);

                }
            }
        }

    }
    public void teacherAssignCourse(HashMap<String ,  ArrayList<String>> teacherCoursesHashMap ,HashMap<String , Course> courseHashMap , Teacher teacher ){

        String id = teacher.getId();
        boolean back = false;
        if (!teacherCoursesHashMap.containsKey(id)){
            teacherCoursesHashMap.put(id,new ArrayList<>());
        }
        do {


            ArrayList<String> teacherCourses = teacherCoursesHashMap.get(id);
            ArrayList<Course> courses = new ArrayList<>(courseHashMap.values());

            if (teacherCourses.size() >= 3) {
                System.out.println("You reached your limit you have 3 courses");
                System.out.println("Do you want to to withdraw a course? (y/n)");
                String choice = input.next();
                if (choice.equals("y") || choice.equals("Y")) {
                    teacherWithdrawCourse(teacherCoursesHashMap, teacher, courseHashMap);
                }

            }


            teacherViewAvailableCourses(teacherCoursesHashMap, courseHashMap, teacher);
            System.out.println("Choose the name of the course you want to assign");
            input.nextLine();
            String name = input.nextLine();
            Course course;
            for (Course cours : courses) {
                course = cours;
                if (course.getName().equals(name)) {
                    teacherCourses.add(course.getId());
                    teacherCoursesHashMap.put(id, teacherCourses);
                    System.out.println(name + " course added successfully");
                    break;
                }
            }
            System.out.println("Do you want to assign another course? (y/n)");
            String choice = input.next();
            if (choice.equals("y")||choice.equals("Y")) back = true;
        }while(!back);
    }
    public  void teacherWithdrawCourse(HashMap<String , ArrayList<String>> teacherCourses , Teacher teacher , HashMap<String , Course>courseHashMap){
        System.out.println("        Your courses");
        teacherViewCourses(teacherCourses,teacher,courseHashMap);
        ArrayList<Course>courses = new ArrayList<>(courseHashMap.values());
        ArrayList<String > teacherCoursesArrayList= teacherCourses.get(teacher.getId());
        System.out.println("Enter the name of the course you want to withdraw ");
        input.nextLine();
        String name = input.nextLine();
        for (Course course : courses) {
            if (course.getName().equals(name)) {
                teacherCoursesArrayList.remove(course.getId());
                teacherCourses.put(teacher.getId(), teacherCoursesArrayList);
                System.out.println("Course withdrawal is successful");
                break;
            }
        }


    }
    public void teacherViewCourses(HashMap<String , ArrayList<String>> teacherCourses , Teacher teacher , HashMap<String , Course>courseHashMap){

        String id = teacher.getId();
        ArrayList<String> courses = teacherCourses.get(id);
        ArrayList<Course> courseArrayList = new ArrayList<>(courseHashMap.values());

        for (String cours : courses) {
            for (Course course : courseArrayList) {

                if (course.getId().equals(cours)) {
                    System.out.println(course);
                    break;
                }
            }
        }




    }

    public void teacherViewStudents(HashMap<String , Account> accountHashMap , HashMap<String , ArrayList<String>> teacherCoursesHashMap ,HashMap<String , ArrayList<String>> studentCourses , Teacher teacher ){

        String id = teacher.getId();
        ArrayList<Account> accounts1 = new ArrayList<>(accountHashMap.values());
        ArrayList<Student> students= new ArrayList<>();
        for (Account account : accounts1) {
            if (account.type == Account.Type.STUDENT)
                students.add((Student) account);

        }

        ArrayList<String> studentIds = new ArrayList<>();
        ArrayList<String> teacherCourses = teacherCoursesHashMap.get(id);
        for (String course : teacherCourses) {

            for (Map.Entry studentCourse : studentCourses.entrySet()) {
                if (((ArrayList<String>) studentCourse.getValue()).contains(course)) {
                    studentIds.add((String) studentCourse.getKey());

                }
            }
        }
        for (Student student : students) {
            for (String studentId : studentIds) {

                if (student.getId().equals(studentId)) {
                    System.out.println(student);
                    break;

                }
            }
        }
    }
}
