package deportes_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import deportes_api.model.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
}