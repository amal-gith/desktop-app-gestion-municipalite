package entity;

public class login {
    private String Username;
    private String Password;

    public login(String Username , String Password) {
        this.Username = Username;
        this.Password=Password;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
