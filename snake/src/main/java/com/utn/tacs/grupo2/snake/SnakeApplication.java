package com.utn.tacs.grupo2.snake;

import com.utn.tacs.grupo2.snake.telegram.TelegramUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class SnakeApplication {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(SnakeApplication.class, args);

        Boolean estaEnProduccion = true;
        TelegramUtils.Start(estaEnProduccion);

    }
}
