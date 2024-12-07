package jsges.nails.mappers;

import jsges.nails.DTO.articulos.ArticuloVentaDTO;
import jsges.nails.domain.articulos.ArticuloVenta;
import jsges.nails.domain.articulos.Linea;

public abstract class ArticuloVentaMapper {

    public static ArticuloVentaDTO toDTO(ArticuloVenta entity) {
        if(entity == null) {
            return null;
        }
        ArticuloVentaDTO dto = new ArticuloVentaDTO();
        dto.setId(entity.getId());
        dto.setDenominacion(entity.getDenominacion());
        dto.setObservacion(entity.getObservacion());
        dto.setLinea(entity.getLinea().getId());
        return dto;
    }

    public static ArticuloVenta toEntity(ArticuloVentaDTO dto, Linea linea) {
        if(dto == null) {
            return null;
        }
        ArticuloVenta entity = new ArticuloVenta();
        entity.setId(dto.getId());
        entity.setDenominacion(dto.getDenominacion());
        entity.setObservacion(dto.getObservacion());
        entity.setLinea(linea);
        return entity;
    }

}
