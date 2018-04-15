package com.utn.tacs.grupo2.snake;

import com.utn.tacs.grupo2.snake.telegram.TelegramUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SnakeApplication {

	public static void main(String[] args) {
            SpringApplication.run(SnakeApplication.class, args);
                
            TelegramUtils.Start();
                
	}
}
