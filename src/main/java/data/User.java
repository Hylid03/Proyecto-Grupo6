package data;

public class User {
    private String username;
    private String password;
    private String email;
    private int role;

    public User(String username, String password, String email,int role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getEmail(){return email;}

    public void setEmail(String email){this.email=email;}

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
