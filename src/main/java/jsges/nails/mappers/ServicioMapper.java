package jsges.nails.mappers;

import jsges.nails.DTO.servicios.ServicioDTO;
import jsges.nails.domain.servicios.ItemServicio;
import jsges.nails.domain.servicios.Servicio;

import java.util.List;

public abstract class ServicioMapper {

    public static ServicioDTO toDTO(Servicio model) {
        ServicioDTO dto = new ServicioDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setObservacion(model.getObservacion());
        dto.setTotal(model.getTotal());
        return dto;
    }

    public static Servicio toEntity(ServicioDTO dto) {
        Servicio model = new Servicio();
        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setObservacion(dto.getObservacion());
        model.setTotal(dto.getTotal());
        return model;
    }



}
