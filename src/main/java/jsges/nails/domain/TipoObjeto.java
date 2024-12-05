package jsges.nails.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class TipoObjeto implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_objeto_id_seq")
        @SequenceGenerator(name = "tipo_objeto_id_seq", sequenceName = "tipo_objeto_id_seq", allocationSize = 1)
        private Integer id;

        @Column(columnDefinition = "TEXT")
        private String nombre;
        private int estado;

        @Column(columnDefinition = "TEXT")
        private String observacion;


        public void asEliminado() {
            this.setEstado(1);
        }

}
