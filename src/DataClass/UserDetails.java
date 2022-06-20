package DataClass;

public class UserDetails {

    private String userId;
    private String password;


    public UserDetails(String userId, String passwrd) {
        this.userId = userId;
        this.password = passwrd;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }


    public String toString() {
        return "DataClass.UserDetails{" +
                ", userId=" + userId +
                ", password='" + password + '\'' +
                '}';
    }
}
