package mc.guillem.pizzas.security;

public class AccountCredentials {

    private String email;
    private String password;


    public AccountCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AccountCredentials() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emil) {
        this.email = emil;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}