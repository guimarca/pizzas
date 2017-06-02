package mc.guillem.pizzas.controllers;


import mc.guillem.pizzas.models.Usuario;
import mc.guillem.pizzas.repositories.UsuarioRepository;
import mc.guillem.pizzas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by guillem on 29/5/17.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Usuario>> getAllUsuarios(){
        return new ResponseEntity<>((Collection<Usuario>) usuarioRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Usuario> getUsuarioWithId(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addUsuario(@RequestBody Usuario input) {
        userService.save(input);
        return new ResponseEntity<String>("", HttpStatus.CREATED);
    }
}
