package jsges.nails.DTO.servicios;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoServicioDTO  {

    @Positive
    public Integer id;

    @NotNull
    @Size(min = 3, max = 64, message = "EL NOMBRE DEBE IR DE ENTRE 4 Y 64 CARACTERES")
    @Pattern(regexp = "^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$", message = "SÓLO PUEDEN HABER LETRAS Y NÚMEROS, NO TIENE QUE HABER ESPACIOS DOBLES NI ESPACIO AL INICIO NI AL FINAL")
    public String denominacion;

    @NotNull
    @Size(max = 240, message = "LA OBSERVACIÓN NO PUEDE SUPERAR LOS 240 CARACTERES")
    @Pattern(regexp = "^[^\\s]+(\\s[^\\s]+)*$", message = "LA OBSERVACIÓN NO TIENE QUE HABER ESPACIOS DOBLES NI ESPACIO AL INICIO NI AL FINAL, NI PUEDE ESTAR VACÍO")
    public String detalle;

    public int codigo;

}
