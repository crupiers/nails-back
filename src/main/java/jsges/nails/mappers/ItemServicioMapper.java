package jsges.nails.mappers;

import jsges.nails.DTO.servicios.ItemServicioDTO;
import jsges.nails.domain.servicios.ItemServicio;

public abstract class ItemServicioMapper {

    public static ItemServicioDTO toDTO(ItemServicio model) {
        ItemServicioDTO dto = new ItemServicioDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setObservacion(model.getObservacion());
        dto.setPrecio(model.getPrecio());
        return dto;
    }

    public static ItemServicio toEntity(ItemServicioDTO dto) {
        ItemServicio model = new ItemServicio();
        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setObservacion(dto.getObservacion());
        model.setPrecio(dto.getPrecio());
        return model;
    }

}
