import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class Register {



Scanner input = new Scanner(System.in);
public void signUp(ArrayList<Request> requests , HashMap<String , Account> accounts) {
    Request request = new Request(null, null);
    String id = UUID.randomUUID().toString();

    System.out.println("        Sign up form");
    System.out.println("register as ");
    System.out.println("1-student ");
    System.out.println("2-teacher ");
    int choice = input.nextInt();
    input.nextLine();
    boolean flag ;

    System.out.println("Enter your username:");
    String username = input.nextLine();
    System.out.println("Enter Your Password:");
    String pass = input.nextLine();
    String email;
    do {
        flag = false;
        System.out.println("Enter Your E-mail:");
        email = input.nextLine();


        for (Account a : accounts.values()) {
            if (email.equals(a.e_mail)) {
                System.out.println("This is E-mail is already exists try another one");
                flag = true;
                break;
            }
        }
    }while (flag);
    if (choice == 1) {

        request = new Request(new Student(username, pass, email,id,0, Account.Type.STUDENT), Request.RequestType.SIGN_UP);
    } else if (choice == 2) {
        System.out.println("Enter your specialization:");
        String specialization = input.nextLine();
        request = new Request(new Teacher(username, pass, email,id, specialization, Account.Type.TEACHER), Request.RequestType.SIGN_UP);
    }
    System.out.println("Your request is sent to admin to be reviewed");
    requests.add(request);

}




}
