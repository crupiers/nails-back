package jsges.nails.mappers;

import jsges.nails.DTO.articulos.ArticuloVentaDTO;
import jsges.nails.domain.articulos.ArticuloVenta;

public abstract class ArticuloVentaMapper {

    public static ArticuloVentaDTO toDTO(ArticuloVenta entity) {
        if(entity == null) {
            return null;
        }
        ArticuloVentaDTO dto = new ArticuloVentaDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setObservacion(entity.getObservacion());
        dto.setLinea(entity.getLinea().getId());
        return dto;
    }

    public static ArticuloVenta toEntity(ArticuloVentaDTO dto) {
        if(dto == null) {
            return null;
        }
        ArticuloVenta entity = new ArticuloVenta();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setObservacion(dto.getObservacion());
        return entity;
    }

}
