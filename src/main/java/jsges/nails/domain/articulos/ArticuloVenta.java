package jsges.nails.domain.articulos;

import jakarta.persistence.*;
import jsges.nails.domain.TipoObjeto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticuloVenta extends TipoObjeto {

        @ManyToOne(cascade = CascadeType.ALL)
        private Linea linea;

}

