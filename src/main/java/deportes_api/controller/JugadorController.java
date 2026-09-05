package deportes_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/jugadores")
    public List<Jugador> listarJugadores() {
        return jugadorRepository.findAll();
    }

    @GetMapping("/jugadores/{id}")
    public ResponseEntity<Jugador> buscarJugador(@PathVariable Long id) {

        return jugadorRepository.findById(id)
                .map(jugador -> ResponseEntity.ok(jugador))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/jugadores/buscar")
    public List<Jugador> buscarPorDeporte(
            @RequestParam String deporte) {

        return jugadorRepository.findByDeporteIgnoreCase(deporte);
    }

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
}