package com.utn.tacs.grupo2.snake;

import com.utn.tacs.grupo2.snake.telegram.TelegramUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

@SpringBootApplication
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class SnakeApplication {

    public static void main(String[] args) throws URISyntaxException {

        SpringApplication application = new SpringApplication(SnakeApplication.class);

        Map<String, Object> propiedades = new HashMap<String, Object>();

        URI jdbUri = new URI(System.getenv("JAWSDB_URL"));

        String dbUsuario = jdbUri.getUserInfo().split(":")[0];
        String dbPass = jdbUri.getUserInfo().split(":")[1];
        String port = String.valueOf(jdbUri.getPort());
        String dbURL = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();

        if (dbPass == null) {

            dbUsuario = "snake";
            dbPass = "snake";
            dbURL = "jdbc:mysql://localhost/snake";

        }

        System.out.println("################");
        System.out.println(dbURL);
        System.out.println(dbUsuario);
        System.out.println(dbPass);

        propiedades.put("spring.datasource.url", dbURL);
        propiedades.put("spring.datasource.password", dbPass);
        propiedades.put("spring.datasource.username", dbUsuario);

        application.setDefaultProperties(propiedades);
        application.run(args);

        Boolean estaEnProduccion = System.getenv("TELEGRAMBOT") != null;
        TelegramUtils.Start(estaEnProduccion);
    }
}
