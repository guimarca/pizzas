package mc.guillem.pizzas.repositories;

import mc.guillem.pizzas.models.Comentario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by guillem on 29/5/17.
 */
public interface ComentarioRepository extends CrudRepository<Comentario, Long> {
    //List<Comentario> findByPizza(int id_pizza);
}