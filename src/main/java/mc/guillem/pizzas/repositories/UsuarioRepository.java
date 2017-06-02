package mc.guillem.pizzas.repositories;

import mc.guillem.pizzas.models.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by guillem on 29/5/17.
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}