package mc.guillem.pizzas.config;

import org.springframework.context.annotation.Configuration;

/**
 * Created by guillem on 1/6/17.
 */
@Configuration
public class Config {
    public static String IMG_PATH;

    public static final String GITHUB_CLIENT_SECRET = "9fbf3cc90c8a72a3e35bba62c9084a48a91c757b";
    public static final String GITHUB_OAUTH_API_URL = "https://github.com/login/oauth/access_token";
    public static final String GITHUB_USERS_API_URL = "https://api.github.com/user";
}
