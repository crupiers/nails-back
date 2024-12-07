package jsges.nails.domain.servicios;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class TipoServicio  {

    public static final int ESTADO_DISPONIBLE = 0;
    public static final int ESTADO_ELIMINADO = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 64)
    private String denominacion;
    @Column(nullable = false)
    private int estado = ESTADO_DISPONIBLE;
    @Column(columnDefinition = "TEXT")
    @Size(max = 240)
    private String detalle;

    private int codigo;


    public void asEliminado() {
        this.setEstado(ESTADO_ELIMINADO);
    }

}
