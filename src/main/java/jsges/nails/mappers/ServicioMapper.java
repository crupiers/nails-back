package jsges.nails.mappers;

import jsges.nails.DTO.servicios.ServicioDTO;
import jsges.nails.domain.organizacion.Cliente;
import jsges.nails.domain.servicios.Servicio;

public abstract class ServicioMapper {

    public static ServicioDTO toDTO(Servicio entity) {
        if(entity == null) {
            return null;
        }
        ServicioDTO dto = new ServicioDTO();
        dto.setId(entity.getId());
        dto.setCliente(entity.getCliente().getId());
        dto.setClienteRazonSocial(entity.getCliente().getRazonSocial());
        dto.setFechaRegistro(entity.getFechaRegistro());
        dto.setFechaRealizacion(entity.getFechaRealizacion());
        dto.setTotal(entity.getTotal());
        return dto;
    }

    public static Servicio toEntity(ServicioDTO dto, Cliente cliente) {
        if(dto == null) {
            return null;
        }
        Servicio entity = new Servicio();
        entity.setId(dto.getId());
        entity.setCliente(cliente);
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setFechaRealizacion(dto.getFechaRealizacion());
        entity.setTotal(dto.getTotal());
        return entity;
    }

    public static Servicio toEntity(ServicioDTO dto) {
        if(dto == null) {
            return null;
        }
        Servicio entity = new Servicio();
        entity.setId(dto.getId());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setFechaRealizacion(dto.getFechaRealizacion());
        entity.setTotal(dto.getTotal());
        return entity;
    }



}
