public class Course {

    private String name;
    private String id;
    private int hours;
    private int maxStudent;
    private String specialization;
    private double price ;
public Course(){}
    public Course(String name, int hours, int maxStudent, String specialization , String id) {
        this.name = name;
        this.hours = hours;
        this.maxStudent = maxStudent;
        this.specialization = specialization;
        this.id  = id;
        price = 1500*hours;

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public String getSpecialization() {
        return specialization;
    }
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return  "Course name: "+name +"\n"+ "Course id: "+id +"\n"+"Course max number of students: "+maxStudent+"\n"+"Course specialization: "+specialization+"\n"+"Course credit hours: "+hours+"\n"+"------------------------------------------------------------------";
    }
}
