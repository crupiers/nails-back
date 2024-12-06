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
import java.util.Optional;
import java.util.regex.Pattern;

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

        cliente.setId(1); //mockito no agrega id automaticamente, devolveria un null
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        Cliente clienteGuardado = clienteService.guardar(cliente);

        //------------------------------------------------------------------------

        assertNotNull(clienteGuardado);
        assertNotEquals(clienteGuardado.getId(),null);

        assertEquals(cliente.getId(), clienteGuardado.getId());
        assertEquals(cliente.getNombre(), clienteGuardado.getNombre());
        assertEquals(cliente.getRazonSocial(), clienteGuardado.getRazonSocial());
        assertEquals(cliente.getMail(), clienteGuardado.getMail());
        assertEquals(cliente.getFechaInicio(), clienteGuardado.getFechaInicio());

    }

    @Test
    void idClientesDiferentes() {

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

        cliente.setId(1);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        Cliente clienteGuardado1 = clienteService.guardar(cliente);

        //------------------------------------------------------------------------

        cliente = new Cliente();

        nombre = "Tomás García";
        observacion = "Segundo cliente";

        razonSocial = "García y Compañía";
        letra = "RI";
        contacto = "Chubut 3500";
        celular = "+5493534009781";
        mail = "tomiycia@gmail.com";
        fechaInicio = new Date(2024, 10, 21);
        fechaNacimiento = new Date(2000, 3, 15);

        cliente.setNombre(nombre);
        cliente.setObservacion(observacion);

        cliente.setRazonSocial(razonSocial);
        cliente.setLetra(letra);
        cliente.setContacto(contacto);
        cliente.setCelular(celular);
        cliente.setMail(mail);
        cliente.setFechaInicio(fechaInicio);
        cliente.setFechaNacimiento(fechaNacimiento);

        cliente.setId(2);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        Cliente clienteGuardado2 = clienteService.guardar(cliente);

        //------------------------------------------------------------------------

        when(clienteRepository.findById(1)).thenReturn(Optional.ofNullable(clienteGuardado1));
        Cliente clienteExiste1 = clienteService.buscarPorId(1);

        when(clienteRepository.findById(2)).thenReturn(Optional.ofNullable(clienteGuardado2));
        Cliente clienteExiste2 = clienteService.buscarPorId(2);

        //------------------------------------------------------------------------

        assertNotNull(clienteExiste1);
        assertNotNull(clienteExiste2);

        assertNotEquals(clienteExiste1.getId(), clienteExiste2.getId());
        assertEquals(clienteExiste1.getId(), 1);
        assertEquals(clienteExiste2.getId(), 2);

    }

    @Test
    void emailNoValido() {

        cliente = new Cliente();

        nombre = "Paulina Giménez";
        observacion = "Una cliente";

        razonSocial = "Paulita SA";
        letra = "M";
        contacto = "Chacabuco 2000";
        celular = "+5493534899766";
        //mail = "juancitosa@hotmail.com";
        fechaInicio = new Date(2023, 2, 8);
        fechaNacimiento = new Date(2007, 9, 20);

        cliente.setNombre(nombre);
        cliente.setObservacion(observacion);

        cliente.setRazonSocial(razonSocial);
        cliente.setLetra(letra);
        cliente.setContacto(contacto);
        cliente.setCelular(celular);
        //cliente.setMail(mail);
        cliente.setFechaInicio(fechaInicio);
        cliente.setFechaNacimiento(fechaNacimiento);

        //------------------------------------------------------------------------

        cliente.setMail("no_valid@ooo");

        cliente.setId(3);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        Cliente clienteGuardado = clienteService.guardar(cliente);

        String emailAddress = clienteGuardado.getMail();
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern emailValidation = Pattern.compile(regexPattern);
        assertFalse(emailValidation.matcher(emailAddress).matches());

        //------------------------------------------------------------------------

        cliente.setMail("mail__@valido.com.ar");

        cliente.setId(3);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        clienteGuardado = clienteService.guardar(cliente);

        emailAddress = clienteGuardado.getMail();
        //regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        //emailValidation = Pattern.compile(regexPattern);
        assertTrue(emailValidation.matcher(emailAddress).matches());

    }

}
