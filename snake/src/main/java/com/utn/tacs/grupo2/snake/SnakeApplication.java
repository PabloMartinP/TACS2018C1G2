package com.utn.tacs.grupo2.snake;

import com.utn.tacs.grupo2.snake.telegram.TelegramUtils;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

@SpringBootApplication
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class SnakeApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SnakeApplication.class, args);

        SpringApplication application = new SpringApplication(SnakeApplication.class);

        Map<String, Object> propiedades = new HashMap<String, Object>();

        String dbUsuario = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASS");
        String dbURL = System.getenv("DB_URL");

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
