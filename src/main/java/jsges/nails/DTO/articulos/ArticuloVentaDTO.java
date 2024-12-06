package jsges.nails.DTO.articulos;

import jsges.nails.DTO.TipoObjetoDTO;
import jsges.nails.domain.articulos.ArticuloVenta;
import lombok.Data;

@Data
public class ArticuloVentaDTO extends TipoObjetoDTO {

    //public Integer id;
    //public String denominacion;
    public Integer linea;

    public ArticuloVentaDTO( ArticuloVenta model) {
        this.id = model.getId();
        this.nombre=model.getNombre();
        this.linea=model.getLinea().getId();
    }

    public ArticuloVentaDTO( ) {

    }
}
