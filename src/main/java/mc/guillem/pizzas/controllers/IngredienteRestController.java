package mc.guillem.pizzas.controllers;

import mc.guillem.pizzas.models.Ingrediente;
import mc.guillem.pizzas.models.Pizza;
import mc.guillem.pizzas.repositories.IngredienteRepository;
import mc.guillem.pizzas.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by guillem on 29/5/17.
 */
@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteRestController {
    @Autowired
    private IngredienteRepository repo;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Ingrediente>> getAllPizzas(){
        return new ResponseEntity<>((Collection<Ingrediente>) repo.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Ingrediente> getIngredienteWithId(@PathVariable Long id) {
        return new ResponseEntity<>(repo.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addIngrediente(@RequestBody Ingrediente input) {
        return new ResponseEntity<>(repo.save(input), HttpStatus.CREATED);
    }
}
