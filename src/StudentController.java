import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentController {
Scanner input = new Scanner(System.in);
    public void studentRegisterCourse(HashMap<String , Course> courses , HashMap<String , ArrayList<String>>studentCourses, Student student ){
        ArrayList<Course> courseArrayList = new ArrayList<>(courses.values());

        String id = student.getId();
        double balance = student.getBalance();

        if (!courses.containsKey(id)){

            studentCourses.put(id , new ArrayList<>());
        }
        ArrayList<String> studentCoursesArrayList = studentCourses.get(id);
        ArrayList<String> val = studentCourses.get(id);

        System.out.println("            All courses");

        for (Map.Entry entry : courses.entrySet()) {
            Course course = (Course) entry.getValue();
            if (!(studentCoursesArrayList.contains(course.getId()))) {
                System.out.println(course);
            }
        }
        System.out.println();
        String s;
        do{
            String choice;
            do {

                choice="";
                Course course = new Course();
                System.out.println("Choose id of a course you want to enroll ");
                String res = input.next();
                for (Course value : courseArrayList) {

                    course = value;
                    if (course.getId().equals(res)) break;

                }

                if (val.contains(res))
                    System.out.println("This course is already enrolled");
                else {
                    if (course.getId().equals(res)) {

                        if (balance >= course.getPrice()) {
                            val.add(res);
                            studentCourses.put(id, val);
                            System.out.println("your balance before enrollment");
                            System.out.println(balance);
                            System.out.println("Course added successfully");
                            System.out.println("Your balance now");
                            balance -= course.getPrice();
                            student.setBalance(balance);
                            System.out.println(balance);
                        } else {
                            System.out.println("You don't have enough money for this course ");
                            System.out.println("Try to request financial aid");
                            break;
                        }
                    } else {
                        System.out.println("You entered wrong id wanna try again? (y/n)");

                        choice = input.next();
                    }
                }
                if (choice.equals("n") || choice.equals("N")) break;

            }while (choice.equals("y")||choice.equals("Y"));

            System.out.println("Do you want to add another course (y/n)");
            s = input.next();
            if (s.equals("n")||s.equals("N"))break;
        }while (s.equals("y")||s.equals("Y"));
    }

    public void showStudentCourses(HashMap<String , Course> courseHashMap , HashMap<String , ArrayList<String > >studentCoursesHashMap , Student student){
        try {
            ArrayList<String> courses = studentCoursesHashMap.get(student.getId());
            ArrayList<Course> result = new ArrayList<>();

            for (String course : courses) {
                result.add(courseHashMap.get(course));
            }
            for (Course course : result) {
                System.out.println(course);
            }
        }catch (NullPointerException e){
            System.out.println("There is no courses go assign some");
        }

    }
    public void studentFinancialAidRequest(ArrayList<Request> requests , Student student){
        Request request = new Request(student, Request.RequestType.FINANCIAL_AID);
        requests.add(request);
        System.out.println("Your request has been sent to the admin");

    }
    public  void showAllCourses(HashMap<String , Course> courseHashMap){
        for (Map.Entry entry : courseHashMap.entrySet()){
            Course course = (Course) entry.getValue();
            System.out.println(course);
        }
    }
}
