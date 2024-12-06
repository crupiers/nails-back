package jsges.nails.unitarios;

import jsges.nails.DTO.Organizacion.ClienteDTO;
import jsges.nails.controller.organizacion.ClienteControlador;
import jsges.nails.service.organizacion.ClienteService;
import jsges.nails.service.organizacion.IClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

//@ExtendWith(MockitoExtension.class)
@WebMvcTest(ClienteControlador.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    void agregarCliente() throws Exception {

        ClienteDTO clienteNuevo = new ClienteDTO();
        clienteNuevo.setId(1);
        clienteNuevo.setNombre("CLIENTE Nº 1");
        clienteNuevo.setObservacion("cliente de prueba");

        when(clienteService.guardar(clienteNuevo)).thenReturn(clienteNuevo);

        mockMvc.perform(post("http://localhost:8080/nails/clientes")
                        .contentType("application/json")
                        .content("{\"id\":1,\"nombre\":\"CLIENTE Nº 1\",\"observacion\":\"cliente de prueba\"}")

        ).andExpect(status().isOk()
        ).andExpect(jsonPath("$.id",is(1))
        ).andExpect(jsonPath("$.nombre",is("CLIENTE Nº 1"))
        ).andExpect(jsonPath("$.observacion",is("cliente de prueba"))
        ).andExpect(jsonPath("$.razonSocial",is(nullValue()))
        ).andExpect(jsonPath("$.letra",is(nullValue()))
        ).andExpect(jsonPath("$.contacto",is(nullValue()))
        ).andExpect(jsonPath("$.celular",is(nullValue()))
        ).andExpect(jsonPath("$.mail",is(nullValue()))
        ).andExpect(jsonPath("$.fechaInicio",is(nullValue()))
        ).andExpect(jsonPath("$.fechaNacimiento",is(nullValue())));

    }

}
