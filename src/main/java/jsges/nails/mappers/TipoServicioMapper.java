package jsges.nails.mappers;

import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.domain.servicios.TipoServicio;

public abstract class TipoServicioMapper {

    public static TipoServicioDTO toDTO(TipoServicio model) {
        TipoServicioDTO dto = new TipoServicioDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setObservacion(model.getObservacion());
        return dto;
    }

    public static TipoServicio toEntity(TipoServicioDTO dto) {
        TipoServicio model = new TipoServicio();
        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setObservacion(dto.getObservacion());
        return model;
    }

}
