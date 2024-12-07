package jsges.nails.domain.servicios;

import jakarta.persistence.*;
import jsges.nails.domain.organizacion.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@ToString
public class Servicio {

    public static final int ESTADO_DISPONIBLE = 0;
    public static final int ESTADO_ELIMINADO = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int estado = ESTADO_DISPONIBLE;

    public void asEliminado() {
        this.setEstado(ESTADO_ELIMINADO);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    private Timestamp fechaRegistro;
    private Timestamp fechaRealizacion;
    private double total;



    public Servicio() {

    }



}
