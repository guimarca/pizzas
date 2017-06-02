package mc.guillem.pizzas.security;

public class GithubCredentialsTo {

    private String code;
    private String client_id;
    private String redirect_uri;
    private String client_secret;

    public GithubCredentialsTo(GithubCredentials gc) {
        this.code = gc.getCode();
        this.client_id = gc.getClientId();
        this.redirect_uri = gc.getRedirectUri();
        this.client_secret = gc.getClientSecret();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }
}