package jsges.nails.DTO.servicios;

import jsges.nails.domain.servicios.ItemServicio;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ItemServicioDTO extends TipoServicioDTO {
    //private Integer id ;
    private String tipoServicio ;
    private Integer tipoServicioId ;
    private Double precio;
    //private String observaciones;

    public ItemServicioDTO(ItemServicio model) {
        //this.observaciones=model.getObservacion();
        this.precio=model.getPrecio();
        this.tipoServicio=model.getTipoServicio().getNombre();
        this.tipoServicioId=model.getTipoServicio().getId();
        this.id=model.getId();

    }

    public ItemServicioDTO() {

    }
}
