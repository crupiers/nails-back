package jsges.nails.mappers;

import jsges.nails.DTO.articulos.ArticuloVentaDTO;
import jsges.nails.domain.articulos.ArticuloVenta;

public abstract class ArticuloVentaMapper {

    public static ArticuloVentaDTO toDTO(ArticuloVenta entity) {
        ArticuloVentaDTO dto = new ArticuloVentaDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setObservacion(entity.getObservacion());
        dto.setLinea(entity.getLinea().getId());
        return dto;
    }

    public static ArticuloVenta toEntity(ArticuloVentaDTO dto) {
        ArticuloVenta model = new ArticuloVenta();
        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setObservacion(dto.getObservacion());
        return model;
    }

}
