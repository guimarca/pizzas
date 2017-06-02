package mc.guillem.pizzas.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import mc.guillem.pizzas.config.Config;
import mc.guillem.pizzas.models.Usuario;
import mc.guillem.pizzas.repositories.UsuarioRepository;
import mc.guillem.pizzas.security.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by guillem on 29/5/17.
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthRestController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value = "/github")
    @ResponseBody
    public void github(HttpServletRequest req, HttpServletResponse res) throws IOException {
        GithubCredentials creds = new ObjectMapper()
                .readValue(req.getInputStream(), GithubCredentials.class);


        RestTemplate t = new RestTemplate();
        GithubCredentialsTo credsTo = new GithubCredentialsTo(creds);

        // Access Token
        String githubToken = t.postForObject(Config.GITHUB_OAUTH_API_URL, credsTo, String.class);

        // User info
        GithubUser githubUser = t.getForObject(Config.GITHUB_USERS_API_URL+"?"+githubToken, GithubUser.class);

        // Look for the user
        Usuario usuario = usuarioRepository.findByEmail(githubUser.getAccountEmail());
        if (usuario == null) {
            usuario = new Usuario(githubUser.getAccountEmail(), githubToken, githubUser.getAccountName(), "");
            usuarioRepository.save(usuario);
        }

        // Add our JWT token
        TokenAuthenticationService.addAuthentication(res, usuario.getEmail(), String.valueOf(usuario.getId()));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addUsuario(@RequestBody Usuario input) {
        return new ResponseEntity<>(usuarioRepository.save(input), HttpStatus.CREATED);
    }
}
