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
        dto.setNombre(entity.getNombre());
        dto.setObservacion(entity.getObservacion());
        return dto;
    }

    public static Linea toEntity(LineaDTO dto) {
        if(dto == null) {
            return null;
        }
        Linea entity = new Linea();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setObservacion(dto.getObservacion());
        return entity;
    }

}
