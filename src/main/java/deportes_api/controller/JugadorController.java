package deportes_api.controller;

import java.util.ArrayList;
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

@RestController
public class JugadorController {

    private final List<Jugador> jugadores = new ArrayList<>();

    public JugadorController() {
        jugadores.add(new Jugador(
            1L,
            "Carlos Pérez",
            "Fútbol",
            "Atlético Nacional"
        ));

        jugadores.add(new Jugador(
            2L,
            "Laura Gómez",
            "Baloncesto",
            "Titanes"
        ));

        jugadores.add(new Jugador(
            3L,
            "Andrés Rodríguez",
            "Tenis",
            "Liga de Tenis"
        ));
    }

    // Obtener todos los jugadores
    @GetMapping("/jugadores")
    public List<Jugador> listarJugadores() {
        return jugadores;
    }

    // Obtener un jugador por ID
    @GetMapping("/jugadores/{id}")
    public Jugador obtenerJugador(@PathVariable Long id) {

        return jugadores.stream()
                .filter(jugador -> jugador.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Buscar jugadores por deporte
    @GetMapping("/jugadores/buscar")
    public List<Jugador> buscarPorDeporte(@RequestParam String deporte) {

        return jugadores.stream()
                .filter(jugador ->
                    jugador.getDeporte().equalsIgnoreCase(deporte)
                )
                .toList();
    }

    // Registrar un nuevo jugador
    @PostMapping("/jugadores")
    public ResponseEntity<Jugador> crearJugador(
            @RequestBody JugadorDTO jugadorDTO) {

        Long nuevoId = (long) jugadores.size() + 1;

        Jugador nuevoJugador = new Jugador(
            nuevoId,
            jugadorDTO.nombre(),
            jugadorDTO.deporte(),
            jugadorDTO.equipo()
        );

        jugadores.add(nuevoJugador);

        return ResponseEntity.status(201).body(nuevoJugador);
    }
}