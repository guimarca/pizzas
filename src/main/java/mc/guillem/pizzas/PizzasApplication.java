package mc.guillem.pizzas;

import javafx.application.Application;
import mc.guillem.pizzas.config.Config;
import mc.guillem.pizzas.models.Comentario;
import mc.guillem.pizzas.models.Ingrediente;
import mc.guillem.pizzas.models.Pizza;
import mc.guillem.pizzas.models.Usuario;
import mc.guillem.pizzas.repositories.ComentarioRepository;
import mc.guillem.pizzas.repositories.IngredienteRepository;
import mc.guillem.pizzas.repositories.PizzaRepository;
import mc.guillem.pizzas.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class PizzasApplication {

	public static void main(String[] args) {
		try {
			Config.IMG_PATH = new File(".").getCanonicalPath() + "/img/";
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpringApplication.run(PizzasApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner initializeDb(PizzaRepository pizzas,
										  UsuarioRepository usuarios,
										  IngredienteRepository ingredientes,
										  ComentarioRepository comentarios){

		return (args) -> {
			// Usuarios
			usuarios.deleteAll();
			usuarios.save(new Usuario("juan@test.com", "juan", "Juan", "Perez Sanz"));
			usuarios.save(new Usuario("pepe@test.com", "pepe", "Pepe", "Ruiz Lopez"));

			// Ingredientes
			ingredientes.deleteAll();
			ingredientes.save(new Ingrediente("Jamón cocido", (float) 0.5));
			ingredientes.save(new Ingrediente("Queso roquefort", (float) 1.5));
			ingredientes.save(new Ingrediente("Piña", (float) 1));
			ingredientes.save(new Ingrediente("Queso parmesano", (float) 1));
			ingredientes.save(new Ingrediente("Pepperoni", (float) 1.5));
			ingredientes.save(new Ingrediente("Aceitunas", (float) 1));
			ingredientes.save(new Ingrediente("Atún", (float) 1));
			ingredientes.save(new Ingrediente("Cebolla", (float) 0.5));


			// Pizzas
			pizzas.deleteAll();
			pizzas.save(new Pizza("Hawaiana",
					"jpg",
					Arrays.asList(
							ingredientes.findOne((long) 1),
							ingredientes.findOne((long) 3)
					)));

			pizzas.save(new Pizza("Cuatro quesos",
					"jpg",
					Arrays.asList(
							ingredientes.findOne((long) 2),
							ingredientes.findOne((long) 4)
					)));

			pizzas.save(new Pizza("Pepperoni",
					"jpg",
					Arrays.asList(
							ingredientes.findOne((long) 5),
							ingredientes.findOne((long) 6)
					)));

			pizzas.save(new Pizza("Atún y cebolla",
					"jpg",
					Arrays.asList(
							ingredientes.findOne((long) 7),
							ingredientes.findOne((long) 8)
					)));

			// Comentarios
			comentarios.deleteAll();
			comentarios.save(new Comentario(
					usuarios.findOne((long) 1),
					pizzas.findOne((long) 1),
					new Date(),
					"Me encantó",
					(float) 8.5
			));
			comentarios.save(new Comentario(
					usuarios.findOne((long) 2),
					pizzas.findOne((long) 1),
					new Date(),
					"No me gustó",
					(float) 2
			));
			comentarios.save(new Comentario(
					usuarios.findOne((long) 1),
					pizzas.findOne((long) 2),
					new Date(),
					"Lo máximo",
					(float) 10
			));
		};
	}

}
