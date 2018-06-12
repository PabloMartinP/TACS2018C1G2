package com.utn.tacs.grupo2.snake;

import com.utn.tacs.grupo2.snake.telegram.TelegramUtils;
import java.net.URISyntaxException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

@SpringBootApplication
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class SnakeApplication {

    public static void main(String[] args) throws URISyntaxException {

        SpringApplication.run(SnakeApplication.class, args);

        Boolean estaEnProduccion = System.getenv("TELEGRAMBOT") != null;
        TelegramUtils.Start(estaEnProduccion);
    }
}
