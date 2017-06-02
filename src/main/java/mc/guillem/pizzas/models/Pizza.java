package mc.guillem.pizzas.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillem on 29/5/17.
 */
@Entity
public class Pizza {
    public static final float PRECIO_BASE = (float) 5;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String foto_extension;

    @ManyToMany
    private List<Ingrediente> ingredientes = new ArrayList<>();

    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
    @JsonManagedReference(value="pizza-comentario")
    @OrderBy("fecha DESC")
    private List<Comentario> comentarios = new ArrayList<>();

    public Pizza(String nombre, String foto_extension, List<Ingrediente> ingredientes) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.foto_extension = foto_extension;
    }

    //for JPA
    public Pizza() {}

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getFoto() {
        return this.id + "." + this.foto_extension;
    }

    public List<Ingrediente> getIngredientes() {
        return this.ingredientes;
    }

    public List<Comentario> getComentarios() {
        return this.comentarios;
    }

    public float getPrecio() {
        float precio = PRECIO_BASE;
        for (Ingrediente ingrediente : ingredientes) {
            precio += ingrediente.getPrecio();
        }
        return precio;
    }

    public void setFoto_extension(String foto_extension) {
        this.foto_extension = foto_extension;
    }
}
