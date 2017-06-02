package mc.guillem.pizzas.repositories;

import mc.guillem.pizzas.models.Ingrediente;
import mc.guillem.pizzas.models.Pizza;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by guillem on 29/5/17.
 */
public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {
    List<Ingrediente> findByIdIn(List<Long> inventoryIdList);
}