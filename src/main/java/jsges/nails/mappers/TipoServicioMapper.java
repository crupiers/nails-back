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
        dto.setCodigo(entity.getCodigo());
        dto.setDenominacion(entity.getDenominacion());
        dto.setDetalle(entity.getDetalle());
        return dto;
    }

    public static TipoServicio toEntity(TipoServicioDTO dto) {
        if(dto == null) {
            return null;
        }
        TipoServicio entity = new TipoServicio();
        entity.setId(dto.getId());
        entity.setCodigo(dto.getCodigo());
        entity.setDenominacion(dto.getDenominacion());
        entity.setDetalle(dto.getDetalle());
        return entity;
    }

}
