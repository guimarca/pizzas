package mc.guillem.pizzas.security;

public class GithubUser {

    private String email;
    private String name;
    private String login;

    public GithubUser() {};

    public GithubUser(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAccountName() {
        if (name != null) {
            return name;
        }
        return login+"@github";
    }

    public String getAccountEmail() {
        if (email != null) {
            return email;
        }
        return login+"@github";
    }
}