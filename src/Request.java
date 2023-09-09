public class Request {

    User user;

    RequestType type;
    public Request(User user , RequestType type){
        this.user = user;
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public RequestType getType() {
        return type;
    }

    public enum RequestType{SIGN_UP,FINANCIAL_AID}

}
