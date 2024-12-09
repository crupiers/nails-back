package jsges.nails.DTO.servicios;

import jsges.nails.domain.servicios.ItemServicio;
import jsges.nails.domain.servicios.Servicio;
import lombok.Data;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ServicioDTO {

    public Integer id;
    public Integer clienteId;
    public Timestamp fechaRegistro;
    public Timestamp fechaRealizacion;
    public Set<ItemServicioDTO> listaItems = new HashSet<>();
    public Double total;
    public String clienteRazonSocial;

    public ServicioDTO() {

    }


    public ServicioDTO(Servicio elemento, List<ItemServicio>list) {

        this.id = elemento.getId();
        this.clienteId = elemento.getCliente().getId();
        this.clienteRazonSocial = elemento.getCliente().getRazonSocial();
        this.fechaRegistro = elemento.getFechaRegistro();
        this.fechaRealizacion = elemento.getFechaRealizacion();
        this.total= elemento.getTotal();

        list.forEach((model) -> {
           listaItems.add(new ItemServicioDTO(model));
        });
    }

    public ServicioDTO(Servicio elemento){

        this.id = elemento.getId();
        this.clienteId = elemento.getCliente().getId();
        this.clienteRazonSocial = elemento.getCliente().getRazonSocial();
        this.fechaRegistro = elemento.getFechaRegistro();
        this.fechaRealizacion = elemento.getFechaRealizacion();
        this.total= elemento.getTotal();


    }
}
