package mc.guillem.pizzas.controllers;

import mc.guillem.pizzas.config.Config;
import mc.guillem.pizzas.models.Ingrediente;
import mc.guillem.pizzas.models.Pizza;
import mc.guillem.pizzas.repositories.PizzaRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;


/**
 * Created by guillem on 29/5/17.
 */
@RestController
@RequestMapping("/api/pizzas")
public class PizzaRestController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Pizza>> getAllPizzas(){
        return new ResponseEntity<>((Collection<Pizza>) pizzaRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Pizza> getPizzaWithId(@PathVariable Long id) {
        return new ResponseEntity<>(pizzaRepository.findOne(id), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addPizza(
                                      @RequestParam String nombre,
                                      @RequestParam String foto_extension,
                                      @RequestParam String ingredientes,
                                      @RequestParam(value = "foto_file", required = true) final MultipartFile foto_file
    ) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Pizza pizza = new Pizza(nombre, foto_extension, mapper.readValue(ingredientes, new TypeReference<List<Ingrediente>>(){}));
        pizza = pizzaRepository.save(pizza);

        File fotoFile = new File(Config.IMG_PATH + pizza.getId() +"."+ foto_extension);
        try {
            foto_file.transferTo(fotoFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<String>("", HttpStatus.CREATED);
    }
}
