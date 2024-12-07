package jsges.nails.domain.organizacion;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente {

        public static final int ESTADO_DISPONIBLE = 0;
        public static final int ESTADO_ELIMINADO = 1;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(columnDefinition = "TEXT", nullable = false, unique = true)
        @Size(min = 3, max = 64)
        private String razonSocial;
        @Column(nullable = false)
        private int estado = ESTADO_DISPONIBLE;

        public void asEliminado() {
                this.setEstado(ESTADO_ELIMINADO);
        }

        @Column(columnDefinition = "TEXT")
        String letra;

        @Column(columnDefinition = "TEXT")
        String contacto;

        @Column(columnDefinition = "TEXT")
        String celular;
        @Column(columnDefinition = "TEXT")
        @Email(message = "EMAIL NO VÁLIDO", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
        String mail;

        Date fechaInicio;
        Date fechaNacimiento;


}
