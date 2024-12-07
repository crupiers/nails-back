package jsges.nails.domain.servicios;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@ToString
public class ItemServicio {

    public static final int ESTADO_DISPONIBLE = 0;
    public static final int ESTADO_ELIMINADO = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int estado = ESTADO_DISPONIBLE;
    @Column(columnDefinition = "TEXT")
    @Size(max = 240)
    private String observacion;

    public void asEliminado() {
        this.setEstado(ESTADO_ELIMINADO);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private TipoServicio tipoServicio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Servicio servicio;

    private Double precio;


    public ItemServicio() {

    }

    public ItemServicio(Servicio servicio, TipoServicio tipoServicio, Double precio,String observacion) {
        this.servicio = servicio;
        this.tipoServicio = tipoServicio;
        this.precio = precio;
        this.setObservacion(observacion);
    }
}
