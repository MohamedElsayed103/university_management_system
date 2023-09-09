import java.util.*;

public class AdminController {
    Scanner input = new Scanner(System.in);

    Account.Type type;
    Request.RequestType type2;
    public void adminHandleRequest(ArrayList<Request> requests , HashMap<String,Account> accounts ){
        //Register register = new Register();
        Student student ;
        Student student1 = null;



        ArrayList<Account> accountArrayList = new ArrayList<>(accounts.values());
        for (int i = 0; i < requests.size(); i++) {
            if (requests.isEmpty()){
                System.out.println("There is no requests :)");
                break;
            }
            Request request = requests.get(i);
            type = request.user.getType();
            type2 = request.type;
            switch (type){
                case STUDENT :

                    student = (Student) request.user;

                    if (type2== Request.RequestType.SIGN_UP) {
                        System.out.println("A student requests to sign up: ");
                        System.out.println(student1);
                    } else if (type2== Request.RequestType.FINANCIAL_AID) {
                        for (Account account : accountArrayList) {
                            if (account.type == Account.Type.STUDENT) {
                                student1 = (Student) account;
                                if (student1.getE_mail().equals(student.getE_mail())) break;
                            }
                        }

                        System.out.println("A student requests a financial aid: ");
                        System.out.println(student);
                    }

                    break;
                case TEACHER:
                    Teacher teacher = (Teacher) request.user;
                    System.out.println(teacher);
                    break;
            }
            System.out.println("Do you want to approve this request? (y/n)");

            String choice = input.next();
            if (choice.equals("y")|| choice.equals("Y")){
                if (type2 == Request.RequestType.FINANCIAL_AID){
                    double balance = student1.getBalance();
                    balance+=5000;
                    student1.setBalance(balance);

                }
                else {
                    accounts.put(request.user.getE_mail(),request.user);


                }
                requests.remove(i);
                i--;

            } else if (choice.equals("N")||choice.equals("n")) {
                requests.remove(i);
                i--;
            }
            if (requests.isEmpty()) {
                System.out.println("There is no requests :)");
                break;
            }
            else{
                System.out.println("Do you want to handle another request? (y/n)");
                String choice2 = input.next();
                if (choice2.equals("N") || choice2.equals("n"))
                    break;
            }
        }

    }


    public void adminAddCourse(HashMap<String ,Course> courses){
        String choice;
        do {
            String id = UUID.randomUUID().toString();
            System.out.print("Enter the name of the Course:");
            String name = input.nextLine();
            System.out.print("Enter number of hours of the Course: ");
            int hours = input.nextInt();
            System.out.print("Enter the maximum number of students to the Course: ");
            int numOfStudents = input.nextInt();
            System.out.print("Enter the specialization of the Course: ");
            input.nextLine();
            String specialization = input.nextLine();

            courses.put(id, new Course(name, hours, numOfStudents, specialization, id));
            System.out.print("New course has been added ");
            System.out.println();
            System.out.println("Do you want to add another course? (y/n)");
            choice= input.next();
            input.nextLine();
        }while(choice.equals("y")||choice.equals("Y"));
    }
    public  void  adminModifyAccounts(HashMap<String , Account> accounts){
        ArrayList<Account> accounts1 = new ArrayList<>(accounts.values());
        System.out.println("        All acounts");
        System.out.println();
        Account account;
        for (int i = 1; i < accounts1.size(); i++) {
            account = accounts1.get(i);
            switch (account.type)
            {
                case STUDENT :
                    Student student = (Student) account;
                    System.out.println("Account number "+i+" is a student");
                    System.out.println(student);
                    System.out.println();
                    break;

                case TEACHER:
                    Teacher teacher = (Teacher) account;
                    System.out.println("Account number "+i+" is a teacher");
                    System.out.println(teacher);
                    System.out.println();
                    break;

            }
        }
        String choice;
        do {

            System.out.println("Select account number you want to modify");
            int choice2 = input.nextInt();
            account = accounts1.get(choice2);
            System.out.println("What do you want to modify?");
            System.out.println("1-Name");

            int choice1;
            if (account.type == Account.Type.STUDENT){
                Student student = (Student) account;
                System.out.println("2-balance");
                choice1 = input.nextInt();
                switch (choice1){
                    case 1:
                        System.out.println("Enter new name");
                        input.nextLine();
                        String name = input.nextLine();
                        student.setName(name);
                        break;

                    case 2:
                        System.out.println("Enter new balance");
                        double balance = input.nextDouble();
                        student.setBalance(balance);
                        break;

                }
            }
            else if (account.type == Account.Type.TEACHER){
                Teacher teacher = (Teacher) account;
                System.out.println("2-specialization");
                choice1 = input.nextInt();

                switch (choice1){
                    case 1:
                        System.out.println("Enter new name");
                        input.nextLine();
                        String name = input.nextLine();
                        teacher.setName(name);
                        break;

                    case 2:
                        System.out.println("Enter new specialization");
                        input.nextLine();
                        String specialization = input.nextLine();
                        teacher.setSpecialization(specialization);
                        break;

                }
            }


            System.out.println("Do you want to modify another account? (y/n)");
            choice = input.next();
        }while (choice.equals("y")||choice.equals("Y"));

    }

    public void adminModifyCourse(HashMap<String ,Course> courses){
        System.out.println("Here are all courses select the id off the course you want to modify");

        showAllCourses(courses);

        System.out.println("Enter the ID: ");
        String id = input.next();
        Course modifiedCourse = courses.get(id);
        System.out.println("choose what do you want to modify ");
        System.out.println("1-Course name");
        System.out.println("2-Course hours");
        System.out.println("3-Course max number of students");
        System.out.println("4-Course specialization");

        int choice = input.nextInt();
        switch (choice){
            case 1:
                System.out.println("Enter the new name: ");
                String name = input.next();
                modifiedCourse.setName(name);
                break;
            case 2:
                System.out.println("enter the new course hours: ");
                int hours = input.nextInt();
                modifiedCourse.setHours(hours);
                break;
            case 3:
                System.out.println("enter the new max number of students: ");
                int max = input.nextInt();
                modifiedCourse.setMaxStudent(max);
                break;
            case 4:
                System.out.println("enter the new course specialization");
                String specialization= input.next();
                modifiedCourse.setSpecialization(specialization);
                break;
        }
        courses.put(id,modifiedCourse);
        System.out.println("Course has been modified");
    }
    public void showAllStudents(HashMap<String , Account> accountHashMap){
        ArrayList<Account> accountArrayList = new ArrayList<>(accountHashMap.values());
        for (Account account : accountArrayList) {

            type = account.type;
            if (type == Account.Type.STUDENT) {
                Student student = (Student) account;
                System.out.println(student);
            }

        }
    }

    public void adminModifyStudentBalance(HashMap<String , Account>accountHashMap){
        System.out.println("        All students");

        showAllStudents(accountHashMap);


        System.out.println("Enter the id of the student");
        String id = input.nextLine();

        for (Map.Entry entry : accountHashMap.entrySet()){
            Account account =(Account) entry.getValue();
            if (account.type == Account.Type.STUDENT) {
                Student student = (Student) account;
                if (student.getId().equals(id)){
                    System.out.println("Student balance before the change");
                    System.out.println(student.getBalance());
                    System.out.println("Enter the new balance");
                    double balance = input.nextDouble();
                    student.setBalance(balance);
                    System.out.println("Balance modified successfully");
                    break;
                }
            }
        }

    }
    public  void showAllCourses(HashMap<String , Course> courseHashMap){
        for (Map.Entry entry : courseHashMap.entrySet()){
            Course course = (Course) entry.getValue();
            System.out.println(course);
        }
    }
}
