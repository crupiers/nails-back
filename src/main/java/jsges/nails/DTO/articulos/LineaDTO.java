package jsges.nails.DTO.articulos;

import jsges.nails.DTO.TipoObjetoDTO;
import jsges.nails.domain.articulos.Linea;
import lombok.Data;

@Data
public class LineaDTO extends TipoObjetoDTO {

    public LineaDTO() {
       super();
    }

    public LineaDTO(Linea linea) {
        this.id= linea.getId();
        this.nombre = linea.getNombre();
    }
}
