package jsges.nails.DTO.servicios;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jsges.nails.domain.servicios.ItemServicio;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ItemServicioDTO  {

    @Positive
    public Integer id;


    @NotNull
    @Size(max = 240, message = "LA OBSERVACIÓN NO PUEDE SUPERAR LOS 240 CARACTERES")
    @Pattern(regexp = "^[^\\s]+(\\s[^\\s]+)*$", message = "LA OBSERVACIÓN NO TIENE QUE HABER ESPACIOS DOBLES NI ESPACIO AL INICIO NI AL FINAL, NI PUEDE ESTAR VACÍO")
    public String observacion;

    //private String tipoServicio;
    private Integer tipoServicioId;

    private Integer servicioId;

    private Double precio;

    public ItemServicioDTO(ItemServicio model) {
        this.observacion=model.getObservacion();
        this.precio=model.getPrecio();
        //this.tipoServicio=model.getTipoServicio().getDenominacion();
        this.tipoServicioId=model.getTipoServicio().getId();
        this.servicioId=model.getServicio().getId();
        this.id=model.getId();
    }

    public ItemServicioDTO() {

    }
}
