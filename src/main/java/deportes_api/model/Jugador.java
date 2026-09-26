package deportes_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String deporte;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    public Jugador() {
    }

    public Jugador(String nombre, String deporte, Equipo equipo) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.equipo = equipo;
    }

    public Jugador(Long id, String nombre, String deporte, Equipo equipo) {
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

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}