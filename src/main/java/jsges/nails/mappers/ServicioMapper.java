package jsges.nails.mappers;

import jsges.nails.DTO.servicios.ServicioDTO;
import jsges.nails.domain.servicios.ItemServicio;
import jsges.nails.domain.servicios.Servicio;

import java.util.List;

public abstract class ServicioMapper {

    public static ServicioDTO toDTO(Servicio entity) {
        if(entity == null) {
            return null;
        }
        ServicioDTO dto = new ServicioDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setObservacion(entity.getObservacion());
        dto.setTotal(entity.getTotal());
        return dto;
    }

    public static Servicio toEntity(ServicioDTO dto) {
        if(dto == null) {
            return null;
        }
        Servicio entity = new Servicio();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setObservacion(dto.getObservacion());
        entity.setTotal(dto.getTotal());
        return entity;
    }



}
