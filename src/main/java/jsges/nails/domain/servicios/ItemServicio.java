package jsges.nails.domain.servicios;

import jakarta.persistence.*;
import jsges.nails.domain.TipoObjeto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@ToString
public class ItemServicio extends TipoObjeto {



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Servicio servicio;

    private Double precio;


    public ItemServicio() {

    }

    public ItemServicio(Servicio servicio, Double precio,String observacion) {
        this.servicio = servicio;
        //this.tipoServicio = tipo;
        this.precio = precio;
        this.setObservacion(observacion);
    }
}
