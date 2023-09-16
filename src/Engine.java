//import java.lang.foreign.VaList;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Engine {
    Scanner input = new Scanner(System.in);
    HashMap<String, Account> accounts = new HashMap<>() ;
    HashMap<String, Course> courseHashMap = new HashMap<>() ;
    HashMap<String,ArrayList<String>> studentCourses = new HashMap<>();
    HashMap<String,ArrayList<String>> teacherCourses = new HashMap<>();
    ArrayList<Request> requests = new ArrayList<>() ;


    Account.Type type;
    Request.RequestType type2;

   FileHandler filehandler = new FileHandler();

   AdminController adminController = new AdminController();
   StudentController studentController = new StudentController();
   TeacherController teacherController = new TeacherController();

    public void go(){

        readData();

        while(true) {

           writeData();

            System.out.println("        Welcome to UMS");
            System.out.println();
            System.out.println("Choose a number");
            System.out.println("1-Login");
            System.out.println("2-Create new account");
            System.out.println("3-exit the program");


            int choice = input.nextInt();
            switch (choice) {
                case 1:

                    Login login = Login.getInstance();
                    Account account = login.loginForm(accounts);
                    System.out.println();
                    if (login.isLogin)
                    {
                        switch (account.type) {
                            case STUDENT:
                                boolean back1 = false;
                                do {
                                    Student student = (Student) account;
                                    System.out.println("Choose a number");
                                    System.out.println("1-Register a course");
                                    System.out.println("2-View your courses");
                                    System.out.println("3-Request a financial aid");
                                    System.out.println("4-Log out");
                                    int choice1 = input.nextInt();
                                    switch (choice1) {
                                        case 1:
                                            studentController.studentRegisterCourse(courseHashMap, studentCourses, student);
                                            break;
                                        case 2:
                                            studentController.showStudentCourses(courseHashMap, studentCourses, student);
                                            break;
                                        case 3:
                                            studentController.studentFinancialAidRequest(requests, student);
                                            break;
                                        case 4:
                                            back1 = true;
                                            break;
                                    }
                                } while (!back1);

                                break;
                            case TEACHER:

                                boolean back2 = false;
                                do {
                                    Teacher teacher = (Teacher) account;
                                    System.out.println("Choose a number");
                                    System.out.println("1-View the list of available courses to be assigned to");
                                    System.out.println("2-Assign a course");
                                    System.out.println("3-Withdraw course assignment");
                                    System.out.println("4-View student data");
                                    System.out.println("5-View your courses");
                                    System.out.println("6-Log out");
                                    int choice2 = input.nextInt();
                                    switch (choice2) {
                                        case 1:
                                            teacherController.teacherViewAvailableCourses(teacherCourses, courseHashMap, teacher);
                                            break;
                                        case 2:
                                            teacherController.teacherAssignCourse(teacherCourses, courseHashMap, teacher);
                                            break;
                                        case 3:
                                            teacherController.teacherWithdrawCourse(teacherCourses, teacher, courseHashMap);
                                            break;
                                        case 4:
                                            teacherController.teacherViewStudents(accounts, teacherCourses, studentCourses, teacher);
                                            break;
                                        case 5:
                                            teacherController.teacherViewCourses(teacherCourses, teacher, courseHashMap);
                                            break;
                                        case 6:
                                            back2 = true;
                                            break;
                                    }

                                } while (!back2);
                                break;
                            case ADMIN:

                                boolean back3 = false;
                                do {

                                    System.out.println("Choose a number");
                                    System.out.println("1-Add course");
                                    System.out.println("2-Modify course");
                                    System.out.println("3-Modify account info");
                                    System.out.println("4-Modify student balance");
                                    System.out.println("5-Explore requests");
                                    System.out.println("6-Log out");

                                    int choice3 = input.nextInt();
                                    input.nextLine();

                                    switch (choice3) {
                                        case 1:
                                            adminController.adminAddCourse(courseHashMap);
                                            //readData.writeCourseData(courseHashMap);
                                            break;
                                        case 2:
                                            adminController.adminModifyCourse(courseHashMap);
                                            //readData.writeCourseData(courseHashMap);
                                            break;
                                        case 3:
                                            adminController.adminModifyAccounts(accounts);
                                            break;
                                        case 4:
                                            adminController.adminModifyStudentBalance(accounts);
                                            break;
                                        case 5:
                                            adminController.adminHandleRequest(requests, accounts);
                                            break;
                                        case 6:
                                            back3=true;
                                            break;

                                    }
                                }while (!back3);
                                    break;
                        }
                    }
                    break;
                case 2:
                        Register register = new Register();
                        register.signUp(requests , accounts);
                        filehandler.writeRequest(requests);

                    break;
                case 3:
                    filehandler.writeAccounts(accounts );
                    filehandler.writeCourseData(courseHashMap);
                    filehandler.writeStudentCourses(studentCourses);
                    filehandler.writeTeacherCourses(teacherCourses);
                    filehandler.writeRequest(requests);
                    System.exit(1);
            }

        }
    }

    //                                      Reading and writing data
    //----------------------------------------------------------------------------------------------------------
    public void readData(){
        type= filehandler.type;
        filehandler.readCourseData();
        courseHashMap = filehandler.courseHashMap;
        filehandler.readStudentCourses();
        studentCourses = filehandler.studentCourses;
        filehandler.readTeacherCourses();
        teacherCourses = filehandler.teacherCourses;
        filehandler.readLoginData();
        accounts= filehandler.accounts;
        filehandler.readRequests();
        requests= filehandler.requests;
        type2 = filehandler.type2;
    }
    public  void writeData(){
        try (ScheduledExecutorService fileWriter = Executors.newScheduledThreadPool(1)) {
            fileWriter.scheduleAtFixedRate(filehandler, 10, 10, TimeUnit.SECONDS);
        }
    }

}
