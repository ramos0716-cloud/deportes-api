package deportes_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import deportes_api.dto.JugadorDTO;
import deportes_api.model.Equipo;
import deportes_api.model.Jugador;
import deportes_api.repository.EquipoRepository;
import deportes_api.repository.JugadorRepository;
import deportes_api.service.LogService;

@RestController
public class JugadorController {

    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;
    private final LogService logService;

    public JugadorController(
            JugadorRepository jugadorRepository,
            EquipoRepository equipoRepository,
            LogService logService) {

        this.jugadorRepository = jugadorRepository;
        this.equipoRepository = equipoRepository;
        this.logService = logService;
    }

    @GetMapping("/jugadores")
    public List<Jugador> listarJugadores() {

        logService.registrarInfo("Consultando lista de jugadores");

        return jugadorRepository.findAll();
    }

    @GetMapping("/jugadores/{id}")
    public ResponseEntity<Jugador> buscarJugador(@PathVariable Long id) {

        return jugadorRepository.findById(id)
                .map(jugador -> {
                    logService.registrarInfo(
                            "Jugador encontrado con ID: " + id
                    );
                    return ResponseEntity.ok(jugador);
                })
                .orElseGet(() -> {
                    logService.registrarWarn(
                            "No se encontró el jugador con ID: " + id
                    );
                    return ResponseEntity.notFound().build();
                });
    }

    @GetMapping("/jugadores/buscar")
    public List<Jugador> buscarPorDeporte(@RequestParam String deporte) {

        logService.registrarInfo(
                "Buscando jugadores por deporte: " + deporte
        );

        return jugadorRepository.findByDeporteIgnoreCase(deporte);
    }

    @PostMapping("/jugadores")
    public ResponseEntity<?> crearJugador(@RequestBody JugadorDTO jugadorDTO) {

        try {

            return equipoRepository.findById(jugadorDTO.equipoId())
                    .map(equipo -> {

                        Jugador nuevoJugador = new Jugador(
                                jugadorDTO.nombre(),
                                jugadorDTO.deporte(),
                                equipo
                        );

                        Jugador jugadorGuardado =
                                jugadorRepository.save(nuevoJugador);

                        logService.registrarInfo(
                                "Jugador creado correctamente: "
                                        + jugadorGuardado.getNombre()
                        );

                        return ResponseEntity
                                .status(201)
                                .body(jugadorGuardado);
                    })
                    .orElseGet(() -> {

                        logService.registrarWarn(
                                "No existe el equipo con ID: "
                                        + jugadorDTO.equipoId()
                        );

                        return ResponseEntity.notFound().build();
                    });

        } catch (Exception e) {

            logService.registrarError(
                    "Error al crear jugador: " + e.getMessage()
            );

            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/jugadores/{id}")
    public ResponseEntity<?> actualizarJugador(
            @PathVariable Long id,
            @RequestBody JugadorDTO jugadorDTO) {

        return jugadorRepository.findById(id)
                .map(jugador -> {

                    Equipo equipo = equipoRepository
                            .findById(jugadorDTO.equipoId())
                            .orElse(null);

                    if (equipo == null) {

                        logService.registrarWarn(
                                "No existe el equipo con ID: "
                                        + jugadorDTO.equipoId()
                        );

                        return ResponseEntity.notFound().build();
                    }

                    jugador.setNombre(jugadorDTO.nombre());
                    jugador.setDeporte(jugadorDTO.deporte());
                    jugador.setEquipo(equipo);

                    Jugador actualizado =
                            jugadorRepository.save(jugador);

                    logService.registrarInfo(
                            "Jugador actualizado con ID: " + id
                    );

                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> {

                    logService.registrarWarn(
                            "No se encontró el jugador con ID: " + id
                    );

                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/jugadores/{id}")
    public ResponseEntity<Void> eliminarJugador(@PathVariable Long id) {

        if (!jugadorRepository.existsById(id)) {

            logService.registrarWarn(
                    "Intento de eliminar jugador inexistente con ID: " + id
            );

            return ResponseEntity.notFound().build();
        }

        jugadorRepository.deleteById(id);

        logService.registrarInfo(
                "Jugador eliminado con ID: " + id
        );

        return ResponseEntity.noContent().build();
    }
}