package jsges.nails.domain;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class TipoObjeto implements Serializable {

        public static final int LONGITUD_MIN_NOMBRE = 4;
        public static final int LONGITUD_MAX_NOMBRE = 64;

        public static final int ESTADO_DISPONIBLE = 0;
        public static final int ESTADO_ELIMINADO = 1;

        //public static final int LONGITUD_MIN_OBSERVACION = 0;
        public static final int LONGITUD_MAX_OBSERVACION = 240;

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_objeto_id_seq")
        @SequenceGenerator(name = "tipo_objeto_id_seq", sequenceName = "tipo_objeto_id_seq", allocationSize = 1)
        private Integer id;

        @Column(columnDefinition = "TEXT", nullable = false, unique = true)
        @Size(min = LONGITUD_MIN_NOMBRE, max = LONGITUD_MAX_NOMBRE)
        private String nombre;
        @Column(nullable = false)
        private int estado = ESTADO_DISPONIBLE;
        @Column(columnDefinition = "TEXT")
        @Size(max = LONGITUD_MAX_OBSERVACION)
        private String observacion;

        public void asEliminado() {
            this.setEstado(TipoObjeto.ESTADO_ELIMINADO);
        }

}
