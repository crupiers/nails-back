package jsges.nails.mappers;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.Linea;

public abstract class LineaMapper {

    public static LineaDTO toDTO(Linea model) {
        LineaDTO dto = new LineaDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setObservacion(model.getObservacion());
        return dto;
    }

    public static Linea toEntity(LineaDTO dto) {
        Linea model = new Linea();
        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setObservacion(dto.getObservacion());
        return model;
    }

}
