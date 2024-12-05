package jsges.nails.DTO.Organizacion;
import jakarta.validation.constraints.Email;
import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.domain.organizacion.Cliente;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClienteDTO extends TipoServicioDTO {

    //private Integer id;
    private String razonSocial;
    private String letra;
    private String contacto;
    private String celular;
    @Email
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
