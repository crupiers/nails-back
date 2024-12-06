package jsges.nails.mappers;

import jsges.nails.DTO.Organizacion.ClienteDTO;
import jsges.nails.domain.organizacion.Cliente;

public abstract class ClienteMapper {

    public static ClienteDTO toDTO(Cliente model) {
        ClienteDTO dto = new ClienteDTO(model);
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setObservacion(model.getObservacion());
        dto.setRazonSocial(model.getRazonSocial());
        dto.setLetra(model.getLetra());
        dto.setContacto(model.getContacto());
        dto.setCelular(model.getCelular());
        dto.setMail(model.getMail());
        dto.setFechaInicio(model.getFechaInicio());
        dto.setFechaNacimiento(model.getFechaNacimiento());
        return dto;
    }

    public static Cliente toEntity(ClienteDTO dto) {
        Cliente model = new Cliente();
        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setObservacion(dto.getObservacion());
        model.setRazonSocial(dto.getRazonSocial());
        model.setLetra(dto.getLetra());
        model.setContacto(dto.getContacto());
        model.setCelular(dto.getCelular());
        model.setMail(dto.getMail());
        model.setFechaInicio(dto.getFechaInicio());
        model.setFechaNacimiento(dto.getFechaNacimiento());
        return model;
    }

}
