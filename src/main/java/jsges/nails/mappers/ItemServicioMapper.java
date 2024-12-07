package jsges.nails.mappers;

import jsges.nails.DTO.servicios.ItemServicioDTO;
import jsges.nails.domain.servicios.ItemServicio;
import jsges.nails.domain.servicios.Servicio;
import jsges.nails.domain.servicios.TipoServicio;

public abstract class ItemServicioMapper {

    public static ItemServicioDTO toDTO(ItemServicio entity) {
        if(entity == null) {
            return null;
        }
        ItemServicioDTO dto = new ItemServicioDTO();
        dto.setId(entity.getId());
        dto.setObservacion(entity.getObservacion());
        dto.setPrecio(entity.getPrecio());
        dto.setTipoServicioId(entity.getTipoServicio().getId());
        //dto.setTipoServicio(entity.getTipoServicio().getDenominacion());
        dto.setServicioId(entity.getServicio().getId());
        return dto;
    }

    public static ItemServicio toEntity(ItemServicioDTO dto, TipoServicio tipoServicio, Servicio servicio) {
        if(dto == null) {
            return null;
        }
        ItemServicio entity = new ItemServicio();
        entity.setId(dto.getId());
        entity.setObservacion(dto.getObservacion());
        entity.setTipoServicio(tipoServicio);
        entity.setServicio(servicio);
        entity.setPrecio(dto.getPrecio());
        return entity;
    }

}
