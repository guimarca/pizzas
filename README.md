# Pizzas

Aplicación web de consulta, valoración y creación de Pizzas. Frontend desarrollado con AngularJS, backend con Spring Boot.

## Ejecutar

Descargar o clonar y ejecutar Spring Boot (por ejemplo, con maven `mvn spring-boot:run`). Más info [aquí](http://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html).

Por defecto la aplicación estará disponible en `http://localhost:8080`.

Hay algunos datos de prueba, entre ellos dos cuentas de usuario:

* pepe@test.com - pw: `pepe`
* juan@test.com - pw: `juan`

## Estrucutra

El backend está en el paquete `mc.guillem.pizzas`, el frontend en `resources/static`. 

## Configuración

Los parámetros de acceso para hacer Login con GitHub están en :

* Frontend: `config.js`, apartado authorization
* Backend: `config\Config.java`

**IMPORTANTE**: La app en GitHub está configurada para funcionar en `http://localhost:8080`.