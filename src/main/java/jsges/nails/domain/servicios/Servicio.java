package jsges.nails.domain.servicios;

import jakarta.persistence.*;
import jsges.nails.domain.TipoObjeto;
import jsges.nails.domain.organizacion.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@ToString
public class Servicio extends TipoObjeto {

        @ManyToOne(cascade = CascadeType.ALL)
        private Cliente cliente;

        private Timestamp fechaRegistro;
        private Timestamp fechaRealizacion;
        private double total;


    public Servicio() {

    }



}
