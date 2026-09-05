package deportes_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import deportes_api.model.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    List<Jugador> findByDeporteIgnoreCase(String deporte);
}