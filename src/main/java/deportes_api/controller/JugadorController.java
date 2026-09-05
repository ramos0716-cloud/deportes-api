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
import deportes_api.model.Jugador;
import deportes_api.repository.JugadorRepository;

@RestController
public class JugadorController {

    private final JugadorRepository jugadorRepository;

    public JugadorController(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    // GET - Listar todos los jugadores
    @GetMapping("/jugadores")
    public List<Jugador> listarJugadores() {
        return jugadorRepository.findAll();
    }

    // GET - Buscar jugador por ID
    @GetMapping("/jugadores/{id}")
    public ResponseEntity<Jugador> buscarJugador(@PathVariable Long id) {

        return jugadorRepository.findById(id)
                .map(jugador -> ResponseEntity.ok(jugador))
                .orElse(ResponseEntity.notFound().build());
    }

    // GET - Buscar jugadores por deporte
    @GetMapping("/jugadores/buscar")
    public List<Jugador> buscarPorDeporte(
            @RequestParam String deporte) {

        return jugadorRepository.findByDeporteIgnoreCase(deporte);
    }

    // POST - Crear jugador
    @PostMapping("/jugadores")
    public ResponseEntity<Jugador> crearJugador(
            @RequestBody JugadorDTO jugadorDTO) {

        Jugador nuevoJugador = new Jugador(
                jugadorDTO.nombre(),
                jugadorDTO.deporte(),
                jugadorDTO.equipo()
        );

        Jugador jugadorGuardado = jugadorRepository.save(nuevoJugador);

        return ResponseEntity
                .status(201)
                .body(jugadorGuardado);
    }

    // PUT - Actualizar jugador
    @PutMapping("/jugadores/{id}")
    public ResponseEntity<Jugador> actualizarJugador(
            @PathVariable Long id,
            @RequestBody JugadorDTO jugadorDTO) {

        return jugadorRepository.findById(id)
                .map(jugador -> {

                    jugador.setNombre(jugadorDTO.nombre());
                    jugador.setDeporte(jugadorDTO.deporte());
                    jugador.setEquipo(jugadorDTO.equipo());

                    Jugador actualizado = jugadorRepository.save(jugador);

                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - Eliminar jugador
    @DeleteMapping("/jugadores/{id}")
    public ResponseEntity<Void> eliminarJugador(@PathVariable Long id) {

        if (!jugadorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        jugadorRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}