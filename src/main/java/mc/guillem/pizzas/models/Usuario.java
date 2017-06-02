package mc.guillem.pizzas.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mc.guillem.pizzas.repositories.UsuarioRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.User;

/**
 * Created by guillem on 29/5/17.
 */
@Entity
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String apellidos;

    @Column(unique = true)
    private String email;

    private String password;


    public Usuario(String email, String password, String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.password = bCryptPasswordEncoder.encode(password);
    }

    //for JPA
    public Usuario() {}

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }
}
