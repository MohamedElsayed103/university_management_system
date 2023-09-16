public class Student extends User{

    private String id ;
    private double balance;
    //String grade;


    public  Student(){}
    public Student(String name, String password, String e_mail , String id , double balance , Type type){
            super( name,  password,  e_mail , type);
            this.id =id;
            this.balance = balance;
    }
    /*public Student(String name , String password , String e_mail , Type type){
        super( name,  password,  e_mail , type);

    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Student name: "+name +"\n"+"Student id: "+ id +"\n"+"Student e-mail: "+e_mail+"\n"+"Student balance: "+balance+"\n"+ "-------------------------------------------------";
    }
}
