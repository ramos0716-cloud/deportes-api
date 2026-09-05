package deportes_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String deporte;
    private String equipo;

    public Jugador() {
    }

    public Jugador(String nombre, String deporte, String equipo) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.equipo = equipo;
    }

    public Jugador(Long id, String nombre, String deporte, String equipo) {
        this.id = id;
        this.nombre = nombre;
        this.deporte = deporte;
        this.equipo = equipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
}