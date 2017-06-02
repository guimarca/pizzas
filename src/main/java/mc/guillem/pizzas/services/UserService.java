package mc.guillem.pizzas.services;


import mc.guillem.pizzas.models.Usuario;
import mc.guillem.pizzas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public void save(Usuario usuario) {
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        //usuario.setRoles(new HashSet<>());
        usuarioRepository.save(usuario);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}