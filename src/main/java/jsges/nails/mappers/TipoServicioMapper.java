package jsges.nails.mappers;

import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.domain.servicios.TipoServicio;

public abstract class TipoServicioMapper {

    public static TipoServicioDTO toDTO(TipoServicio entity) {
        if(entity == null) {
            return null;
        }
        TipoServicioDTO dto = new TipoServicioDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setObservacion(entity.getObservacion());
        return dto;
    }

    public static TipoServicio toEntity(TipoServicioDTO dto) {
        if(dto == null) {
            return null;
        }
        TipoServicio entity = new TipoServicio();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setObservacion(dto.getObservacion());
        return entity;
    }

}
