package deportes_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import deportes_api.model.Equipo;
import deportes_api.repository.EquipoRepository;

@RestController
public class EquipoController {

    private final EquipoRepository equipoRepository;

    public EquipoController(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @GetMapping("/equipos")
    public List<Equipo> listarEquipos() {
        return equipoRepository.findAll();
    }

    @GetMapping("/equipos/{id}")
    public ResponseEntity<Equipo> buscarEquipo(@PathVariable Long id) {
        return equipoRepository.findById(id)
                .map(equipo -> ResponseEntity.ok(equipo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/equipos")
    public ResponseEntity<Equipo> crearEquipo(@RequestBody Equipo equipo) {
        Equipo nuevoEquipo = equipoRepository.save(equipo);
        return ResponseEntity.status(201).body(nuevoEquipo);
    }

    @PutMapping("/equipos/{id}")
    public ResponseEntity<Equipo> actualizarEquipo(
            @PathVariable Long id,
            @RequestBody Equipo datos) {

        return equipoRepository.findById(id)
                .map(equipo -> {
                    equipo.setNombre(datos.getNombre());
                    equipo.setCiudad(datos.getCiudad());

                    Equipo actualizado = equipoRepository.save(equipo);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {

        if (!equipoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        equipoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}