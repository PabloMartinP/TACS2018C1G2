package com.utn.tacs.grupo2.snake.repository;

import com.utn.tacs.grupo2.snake.domain.Transaccion;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    Optional<List<Transaccion>> findByBilleteraUsuarioIdAndMonedaNombre(Long usuarioId, String monedaNombre);

    Long countByFechaGreaterThanEqual(LocalDateTime fecha);

}
