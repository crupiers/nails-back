package jsges.nails.unitarios;

import jsges.nails.domain.organizacion.Cliente;
import jsges.nails.repository.organizacion.ClienteRepository;
import jsges.nails.service.organizacion.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;

public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService = new ClienteService();

    @Mock
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    private String nombre;
    private String observacion;

    private String razonSocial;
    private String letra;
    private String contacto;
    private String celular;
    private String mail;
    private Date fechaInicio;
    private Date fechaNacimiento;

    @BeforeEach
    void antesDeCadaTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearClienteNuevo() {

        cliente = new Cliente();

        nombre = "Juan Pérez";
        observacion = "Primer cliente";

        razonSocial = "Juancito SA";
        letra = "RI";
        contacto = "Miami 2500";
        celular = "+5493534180384";
        mail = "juancitosa@hotmail.com";
        fechaInicio = new Date(2024, 9, 12);
        fechaNacimiento = new Date(2002, 8, 10);

        cliente.setNombre(nombre);
        cliente.setObservacion(observacion);

        cliente.setRazonSocial(razonSocial);
        cliente.setLetra(letra);
        cliente.setContacto(contacto);
        cliente.setCelular(celular);
        cliente.setMail(mail);
        cliente.setFechaInicio(fechaInicio);
        cliente.setFechaNacimiento(fechaNacimiento);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente clienteGuardado = clienteService.guardar(cliente);

        assertNotNull(clienteGuardado);

        assertEquals(cliente.getId(), clienteGuardado.getId());
        assertEquals(cliente.getNombre(), clienteGuardado.getNombre());
        assertEquals(cliente.getRazonSocial(), clienteGuardado.getRazonSocial());
        assertEquals(cliente.getMail(), clienteGuardado.getMail());
        assertEquals(cliente.getFechaInicio(), clienteGuardado.getFechaInicio());

    }

}
