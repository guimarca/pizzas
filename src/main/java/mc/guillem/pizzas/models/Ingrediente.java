package mc.guillem.pizzas.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by guillem on 29/5/17.
 */
@Entity
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private float precio;


    public Ingrediente(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    //for JPA
    public Ingrediente() {}

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public float getPrecio() {
        return this.precio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
