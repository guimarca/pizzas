package mc.guillem.pizzas.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Ignorable;

public class GithubCredentials {

    private String code;
    private String clientId;
    private String redirectUri;
    private String clientSecret = "9fbf3cc90c8a72a3e35bba62c9084a48a91c757b";


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