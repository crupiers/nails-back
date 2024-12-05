package jsges.nails.domain.organizacion;

import jakarta.persistence.*;
import jsges.nails.domain.TipoObjeto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente extends TipoObjeto {

        @Column(columnDefinition = "TEXT")
        String razonSocial;

        @Column(columnDefinition = "TEXT")
        String letra;

        @Column(columnDefinition = "TEXT")
        String contacto;

        @Column(columnDefinition = "TEXT")
        String celular;
        @Column(columnDefinition = "TEXT")
        String mail;

        Date fechaInicio;
        Date fechaNacimiento;


}
