public class Account {
    protected String name;
    protected String password;
    protected String e_mail;
    protected Type type ;

    public Type getType() {
        return type;
    }

    public Account() {}

    public Account(String name, String password, String e_mail , Type type) {
        this.name = name;
        this.password = password;
        this.e_mail = e_mail;
        this.type = type;
    }
    public enum Type{STUDENT,TEACHER,ADMIN}

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getE_mail() {
        return e_mail;
    }
}
