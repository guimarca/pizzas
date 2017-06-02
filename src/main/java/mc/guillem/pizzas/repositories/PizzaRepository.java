package mc.guillem.pizzas.repositories;

import mc.guillem.pizzas.models.Pizza;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by guillem on 29/5/17.
 */
public interface PizzaRepository extends CrudRepository<Pizza, Long> {

}