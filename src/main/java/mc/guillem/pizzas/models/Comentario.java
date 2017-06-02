package mc.guillem.pizzas.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import mc.guillem.pizzas.repositories.PizzaRepository;
import mc.guillem.pizzas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by guillem on 29/5/17.
 */
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Usuario usuario;

    @OneToOne
    @JsonBackReference(value="pizza-comentario")
    private Pizza pizza;

    private Date fecha;
    private String texto;
    private float puntuacion;


    public Comentario(Usuario usuario, Pizza pizza, Date fecha, String texto, float puntuacion) {
        this.usuario = usuario;
        this.pizza = pizza;
        this.fecha = fecha;
        this.texto = texto;
        this.puntuacion = puntuacion;
    }

    //for JPA
    public Comentario() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getFechaFormateada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(fecha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }
}
