package jsges.nails.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jsges.nails.domain.TipoObjeto;
import lombok.Data;

@Data
public class TipoObjetoDTO {

    @NotNull
    @Positive
    public Integer id;

    @NotNull
    @Size(min = TipoObjeto.LONGITUD_MIN_NOMBRE, max = TipoObjeto.LONGITUD_MAX_NOMBRE, message = "EL NOMBRE DEBE IR DE ENTRE 4 Y 64 CARACTERES")
    @Pattern(regexp = "^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$", message = "SÓLO PUEDEN HABER LETRAS Y NÚMEROS, NO TIENE QUE HABER ESPACIOS DOBLES NI ESPACIO AL INICIO NI AL FINAL")
    public String nombre;

    @NotNull
    @Size(max = TipoObjeto.LONGITUD_MAX_OBSERVACION, message = "LA OBSERVACIÓN NO PUEDE SUPERAR LOS 240 CARACTERES")
    @Pattern(regexp = "^[^\\s]+(\\s[^\\s]+)*$", message = "LA OBSERVACIÓN NO TIENE QUE HABER ESPACIOS DOBLES NI ESPACIO AL INICIO NI AL FINAL, NI PUEDE ESTAR VACÍO")
    public String observacion;
}
