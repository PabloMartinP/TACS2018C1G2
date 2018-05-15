package com.utn.tacs.grupo2.snake.repository;

import com.utn.tacs.grupo2.snake.domain.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

    Optional<Usuario> findByUsernameIgnoreCase(String username);

    Usuario findByTelegramId(long telegramId);

    Usuario findByUsernameAndTelegramId(String username, long telegramId);

}
