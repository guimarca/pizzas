package mc.guillem.pizzas.controllers;

import mc.guillem.pizzas.models.Comentario;
import mc.guillem.pizzas.models.Pizza;
import mc.guillem.pizzas.models.Usuario;
import mc.guillem.pizzas.repositories.ComentarioRepository;
import mc.guillem.pizzas.repositories.PizzaRepository;
import mc.guillem.pizzas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Created by guillem on 29/5/17.
 */
@RestController
@RequestMapping("/api/comentarios")
public class ComentarioRestController {
    @Autowired
    private ComentarioRepository repo;

    @Autowired
    private PizzaRepository repoPizza;

    @Autowired
    private UsuarioRepository repoUsuario;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Comentario>> getAllComentarios(){
        return new ResponseEntity<>((Collection<Comentario>) repo.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Comentario> getComentarioWithId(@PathVariable Long id) {
        return new ResponseEntity<>(repo.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addComentario(@RequestBody Comentario input) throws ParseException {
        return new ResponseEntity<>(repo.save(input), HttpStatus.CREATED);
    }
}
