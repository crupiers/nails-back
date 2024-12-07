package jsges.nails.DTO.Organizacion;
import jakarta.validation.constraints.*;
import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.domain.organizacion.Cliente;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
public class ClienteDTO  {

    @Positive
    public Integer id;

    @NotNull
    @Size(min = 3, max = 64, message = "EL NOMBRE DEBE IR DE ENTRE 4 Y 64 CARACTERES")
    @Pattern(regexp = "^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$", message = "SÓLO PUEDEN HABER LETRAS Y NÚMEROS, NO TIENE QUE HABER ESPACIOS DOBLES NI ESPACIO AL INICIO NI AL FINAL")
    public String razonSocial;

    private String letra;
    private String contacto;
    private String celular;
    @Email(message = "EMAIL NO VÁLIDO", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String mail;
    private Date fechaInicio;
    private Date fechaNacimiento;

    public ClienteDTO(Cliente model) {
        this.id = model.getId();
        this.razonSocial=model.getRazonSocial();
        this.celular=model.getCelular();
        this.mail=model.getMail();

    }

    public ClienteDTO( ) {

    }
}
