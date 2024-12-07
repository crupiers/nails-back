package jsges.nails.mappers;

import jsges.nails.DTO.Organizacion.ClienteDTO;
import jsges.nails.domain.organizacion.Cliente;

public abstract class ClienteMapper {

    public static ClienteDTO toDTO(Cliente entity) {
        if(entity == null) {
            return null;
        }
        ClienteDTO dto = new ClienteDTO(entity);
        dto.setId(entity.getId());
        dto.setRazonSocial(entity.getRazonSocial());
        dto.setLetra(entity.getLetra());
        dto.setContacto(entity.getContacto());
        dto.setCelular(entity.getCelular());
        dto.setMail(entity.getMail());
        dto.setFechaInicio(entity.getFechaInicio());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        return dto;
    }

    public static Cliente toEntity(ClienteDTO dto) {
        if(dto == null) {
            return null;
        }
        Cliente entity = new Cliente();
        entity.setId(dto.getId());
        entity.setRazonSocial(dto.getRazonSocial());
        entity.setLetra(dto.getLetra());
        entity.setContacto(dto.getContacto());
        entity.setCelular(dto.getCelular());
        entity.setMail(dto.getMail());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        return entity;
    }

}
