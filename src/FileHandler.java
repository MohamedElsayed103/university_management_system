import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// TODO: 9/6/2023  remove implements Runnable
public class FileHandler implements Runnable {
    HashMap<String, Account> accounts = new HashMap<>();
    HashMap<String , Course> courseHashMap = new HashMap<>();
    HashMap<String,ArrayList<String>> studentCourses = new HashMap<>();
    HashMap<String,ArrayList<String>> teacherCourses = new HashMap<>();
    ArrayList<Request> requests = new ArrayList<>();
    Account.Type type;
    Request.RequestType type2;

    public void readLoginData(){
        try {

            BufferedReader bf = new BufferedReader(new FileReader("files/accounts/acc.txt"));
            String row ;
            while ((row = bf.readLine())!=null){
                String[] values = row.split("//");
                type = Account.Type.valueOf(values[0].toUpperCase());
                switch (type){
                    case STUDENT:
                        accounts.put(values[3],new Student(values[1],values[2],values[3],values[4],Double.parseDouble(values[5]),type));
                        break;
                    case ADMIN:
                        accounts.put(values[3],new Admin(values[1],values[2],values[3],type));
                        break;
                    case TEACHER:
                        accounts.put(values[3],new Teacher(values[1],values[2],values[3],values[4],values[5],type));

                        break;
                }

            }
            bf.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void readCourseData(){
        try {
            BufferedReader bf = new BufferedReader(new FileReader("files/course/course.txt"));
            String row ;
            while((row = bf.readLine())!=null){
                String[] values = row.split("//");
                courseHashMap.put(values[4],new Course(values[0],Integer.parseInt( values[1]),Integer.parseInt(values[2]),values[3],values[4]));

            }
            bf.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public  void writeCourseData(HashMap<String , Course> courses){
        try {
            BufferedWriter bw= new BufferedWriter(new FileWriter("files/course/course.txt"));
            ArrayList<Course> courseArrayList = new ArrayList<>(courses.values());

            for (Course course : courseArrayList) {
                String ss = course.getName() + "//" + course.getHours() + "//" + course.getMaxStudent() + "//" + course.getSpecialization() + "//" + course.getId();
                bw.write(ss);
                bw.newLine();
            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readStudentCourses(){
        try {
            BufferedReader bf = new BufferedReader(new FileReader("files/student/studentCourses.txt"));
            String row  ;

            while ((row = bf.readLine())!= null){

                String[] values = row.split("//");
                    ArrayList<String>s = new ArrayList<>();
                for (int i = 1; i <values.length ; i++) {
                    s.add(values[i]);
                }

                studentCourses.put(values[0],s);
            }
            bf.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeStudentCourses(HashMap<String,ArrayList<String>> studentCourses){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("files/student/studentCourses.txt"));
            ArrayList<String> stringArrayList  ;
            for (Map.Entry m: studentCourses.entrySet()){
                String ss="";
                stringArrayList=(ArrayList<String>) m.getValue();
                for(String string:stringArrayList){
                    ss+="//"+string;
                }
                String s = m.getKey()+ss;
                bw.write(s);
                bw.newLine();
            }



            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void readTeacherCourses(){
        try {
            BufferedReader bf = new BufferedReader(new FileReader("files/teacher/teacherCourses.txt"));
            String row  ;

            while ((row = bf.readLine())!= null){

                String[] values = row.split("//");
                ArrayList<String>s = new ArrayList<>();
                for (int i = 1; i <values.length ; i++) {
                    s.add(values[i]);
                }
                teacherCourses.put(values[0],s);
            }
            bf.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeTeacherCourses(HashMap<String,ArrayList<String>> teacherCourses){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("files/teacher/teacherCourses.txt"));
            ArrayList<String> stringArrayList  ;
            for (Map.Entry m: teacherCourses.entrySet()){
                String values="";
                stringArrayList=(ArrayList<String>) m.getValue();
                for(String string:stringArrayList){
                    values+="//"+string;
                }
                String row = m.getKey()+values;
                bw.write(row);
                bw.newLine();
            }



            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void readRequests(){
        try {
            BufferedReader bf = new BufferedReader(new FileReader("files/requests/requests.txt"));
            String row ;
            while ((row = bf.readLine())!= null){
                String[] values = row.split("//");
                type = Account.Type.valueOf(values[0].toUpperCase());
                type2 = Request.RequestType.valueOf(values[1].toUpperCase());
                switch (type){
                    case STUDENT :

                        requests.add(new Request( new Student(values[2],values[3],values[4] ,values[5],Double.parseDouble(values[6]) , type),type2));
                        break;

                    case TEACHER:
                        requests.add(new Request( new Teacher(values[2],values[3],values[4],values[5],values[6] ,type),type2));
                        break;
                }
                //bf.close();

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeRequest(ArrayList<Request> requests ){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("files/requests/requests.txt"  ));
            for (Request request : requests) {
                type = request.user.getType();
                type2 = request.type;
                String line = "";
                switch (type) {
                    case STUDENT:
                        Student student = (Student) request.user;
                        line += type + "//" + type2 + "//" + student.getName() + "//" + student.getPassword() + "//" + student.getE_mail() + "//" + student.getId() + "//" + student.getBalance();
                        break;
                    case TEACHER:
                        Teacher teacher = (Teacher) request.user;
                        line += type + "//" + type2 + "//" + teacher.getName() + "//" + teacher.getPassword() + "//" + teacher.getE_mail() + "//" + teacher.getId() + "//" + teacher.getSpecialization();
                        break;
                }
                bw.write(line);
                bw.newLine();
            }
            bw.flush();
            bw.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeAccounts(HashMap<String , Account> accounts ){
        try {
            BufferedWriter bw = new BufferedWriter( new FileWriter("files/accounts//acc.txt"));



            bw.write("ADMIN//admin//123//admin@gmail.com\n");



            for (Map.Entry entry : accounts.entrySet()) {
                Account account = (Account) entry.getValue();
                type = account.type;


                String line = "";
                switch (type) {
                    case STUDENT:
                        Student student = (Student) account;
                        line += type + "//" + student.name + "//" + student.password + "//" + student.e_mail + "//" + student.getId() + "//" + student.getBalance();
                        bw.write(line);


                        bw.newLine();
                        break;
                    case TEACHER:
                        Teacher teacher = (Teacher) account;
                        line += type + "//" + teacher.name + "//" + teacher.password + "//" + teacher.e_mail + "//" + teacher.getId() + "//" + teacher.getSpecialization();
                        bw.write(line);


                        bw.newLine();
                        break;
                }
            }
            bw.flush();

            bw.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void run() {
        FileHandler filehandler = new FileHandler();
        filehandler.writeTeacherCourses(teacherCourses);
        filehandler.writeCourseData(courseHashMap);
        filehandler.writeStudentCourses(studentCourses);
        filehandler.writeAccounts(accounts);
        filehandler.writeRequest(requests);
    }
}
