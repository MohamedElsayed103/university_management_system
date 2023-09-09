public class Teacher extends User {
    private String id;
    private String specialization;

    public Teacher(String name, String password, String e_mail,String id, String specialization , Type type){
        super(name,password,e_mail ,type );
        this.id = id;
        this.specialization = specialization;
    }

    public String getId() {
        return id;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Teacher name: "+name+"\n"+"Teacher id"+id +"\n"+"Teacher e-mail"+ e_mail+"\n"+"Teacher specialization: " +specialization +"\n"+"----------------------------------------------------";
    }
}
