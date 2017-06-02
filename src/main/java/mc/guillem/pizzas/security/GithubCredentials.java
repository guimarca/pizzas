package mc.guillem.pizzas.security;

import mc.guillem.pizzas.config.Config;

public class GithubCredentials {

    private String code;
    private String clientId;
    private String redirectUri;
    private String clientSecret = Config.GITHUB_CLIENT_SECRET;


    public GithubCredentials(String code, String clientId, String redirectUri) {
        this.code = code;
        this.clientId = clientId;
        this.redirectUri = redirectUri;
    }

    public GithubCredentials() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}