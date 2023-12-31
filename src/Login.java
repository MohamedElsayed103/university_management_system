import java.util.*;
import java.lang.*;


public class Login {

    Scanner input = new Scanner(System.in);
    boolean isLogin = false;
    private static Login  login= new Login();
    private Login(){}
    public static Login getInstance(){
        return login;
    }
    public Account loginForm(HashMap<String, Account> accountsHashMap ) {
        Account account;
        System.out.println("            Login Form          ");
        System.out.println();


        System.out.println("Enter Your E-mail: ");
        String email = input.nextLine();
        System.out.println("Enter Your Password: ");
        String pass = input.nextLine();
    if (accountsHashMap.containsKey(email) &&accountsHashMap.get(email).password.equals(pass)) {
         account = accountsHashMap.get(email);
        switch (account.type) {
            case STUDENT:
                Student student = (Student) account;
                System.out.println("login successful as a Student");
                System.out.println("Welcome " + student.name);
                isLogin = true;
                break;
            case TEACHER:
                Teacher teacher = (Teacher) account;
                System.out.println("login successful as a Teacher");
                System.out.println("Welcome " + teacher.name);
                isLogin = true;
                break;

            case ADMIN:
                Admin admin = (Admin) account;
                System.out.println("login successful as an Admin");
                System.out.println("Welcome " + admin.name);
                isLogin = true;
                break;
        }

    }else {
        System.out.println("Login failed pls try again");
        account = null;
    }


        return account;
    }

}
