package jsges.nails.domain.articulos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jsges.nails.DTO.articulos.LineaDTO;
import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString
public class Linea  {

    public static final int ESTADO_DISPONIBLE = 0;
    public static final int ESTADO_ELIMINADO = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT", nullable = false, unique = true)
    @Size(min = 3, max = 64)
    private String denominacion;
    @Column(nullable = false)
    private int estado = ESTADO_DISPONIBLE;
    @Column(columnDefinition = "TEXT")
    @Size(max = 240)
    private String detalle;

    public void asEliminado() {
        this.setEstado(ESTADO_ELIMINADO);
    }

    private int codigo;

    public Linea() {
        // Constructor por defecto necesario para JPA
    }

    public Linea(String nombre) {

        this.setDenominacion(nombre);
    }

    public Linea(LineaDTO model) {
        this.setDenominacion(model.denominacion);
        this.setId(model.getId());
        this.setDetalle(model.getDetalle());
    }
}

