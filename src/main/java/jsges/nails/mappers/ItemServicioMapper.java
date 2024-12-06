package jsges.nails.mappers;

import jsges.nails.DTO.servicios.ItemServicioDTO;
import jsges.nails.domain.servicios.ItemServicio;

public abstract class ItemServicioMapper {

    public static ItemServicioDTO toDTO(ItemServicio entity) {
        if(entity == null) {
            return null;
        }
        ItemServicioDTO dto = new ItemServicioDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setObservacion(entity.getObservacion());
        dto.setPrecio(entity.getPrecio());
        return dto;
    }

    public static ItemServicio toEntity(ItemServicioDTO dto) {
        if(dto == null) {
            return null;
        }
        ItemServicio entity = new ItemServicio();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setObservacion(dto.getObservacion());
        entity.setPrecio(dto.getPrecio());
        return entity;
    }

}
