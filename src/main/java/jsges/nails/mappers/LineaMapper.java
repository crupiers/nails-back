package jsges.nails.mappers;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.Linea;

public abstract class LineaMapper {

    public static LineaDTO toDTO(Linea entity) {
        if(entity == null) {
            return null;
        }
        LineaDTO dto = new LineaDTO();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setDenominacion(entity.getDenominacion());
        dto.setDetalle(entity.getDetalle());
        return dto;
    }

    public static Linea toEntity(LineaDTO dto) {
        if(dto == null) {
            return null;
        }
        Linea entity = new Linea();
        entity.setId(dto.getId());
        entity.setCodigo(dto.getCodigo());
        entity.setDenominacion(dto.getDenominacion());
        entity.setDetalle(dto.getDetalle());
        return entity;
    }

}
