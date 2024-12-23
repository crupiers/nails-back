package jsges.nails.unitarios;

import jsges.nails.DTO.Organizacion.ClienteDTO;
import jsges.nails.domain.organizacion.Cliente;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.mappers.ClienteMapper;
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

    private ClienteDTO clienteDTO;

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

        clienteDTO = new ClienteDTO();

        razonSocial = "Juancito SA";
        letra = "RI";
        contacto = "Miami 2500";
        celular = "+5493534180384";
        mail = "juancitosa@hotmail.com";
        fechaInicio = new Date(2024, 9, 12);
        fechaNacimiento = new Date(2002, 8, 10);

        clienteDTO.setRazonSocial(razonSocial);
        clienteDTO.setLetra(letra);
        clienteDTO.setContacto(contacto);
        clienteDTO.setCelular(celular);
        clienteDTO.setMail(mail);
        clienteDTO.setFechaInicio(fechaInicio);
        clienteDTO.setFechaNacimiento(fechaNacimiento);

        clienteDTO.setId(1); //mockito no agrega id automaticamente, devolveria un null
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        ClienteDTO clienteGuardado = clienteService.guardar(clienteDTO);

        //------------------------------------------------------------------------

        assertNotNull(clienteGuardado);
        assertNotEquals(clienteGuardado.getId(),null);

        assertEquals(clienteDTO.getId(), clienteGuardado.getId());
        assertEquals(clienteDTO.getRazonSocial(), clienteGuardado.getRazonSocial());
        assertEquals(clienteDTO.getMail(), clienteGuardado.getMail());
        assertEquals(clienteDTO.getFechaInicio(), clienteGuardado.getFechaInicio());

    }

    @Test
    void idClientesDiferentes() {

        clienteDTO = new ClienteDTO();

        razonSocial = "Juancito SA";
        letra = "RI";
        contacto = "Miami 2500";
        celular = "+5493534180384";
        mail = "juancitosa@hotmail.com";
        fechaInicio = new Date(2024, 9, 12);
        fechaNacimiento = new Date(2002, 8, 10);

        clienteDTO.setRazonSocial(razonSocial);
        clienteDTO.setLetra(letra);
        clienteDTO.setContacto(contacto);
        clienteDTO.setCelular(celular);
        clienteDTO.setMail(mail);
        clienteDTO.setFechaInicio(fechaInicio);
        clienteDTO.setFechaNacimiento(fechaNacimiento);

        clienteDTO.setId(1);
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        ClienteDTO clienteGuardado1 = clienteService.guardar(clienteDTO);

        //------------------------------------------------------------------------

        clienteDTO = new ClienteDTO();

        razonSocial = "García y Compañía";
        letra = "RI";
        contacto = "Chubut 3500";
        celular = "+5493534009781";
        mail = "tomiycia@gmail.com";
        fechaInicio = new Date(2024, 10, 21);
        fechaNacimiento = new Date(2000, 3, 15);

        clienteDTO.setRazonSocial(razonSocial);
        clienteDTO.setLetra(letra);
        clienteDTO.setContacto(contacto);
        clienteDTO.setCelular(celular);
        clienteDTO.setMail(mail);
        clienteDTO.setFechaInicio(fechaInicio);
        clienteDTO.setFechaNacimiento(fechaNacimiento);

        clienteDTO.setId(2);
        cliente = ClienteMapper.toEntity(clienteDTO);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        ClienteDTO clienteGuardado2 = clienteService.guardar(clienteDTO);

        //------------------------------------------------------------------------

        when(clienteRepository.findById(1)).thenReturn(Optional.ofNullable(ClienteMapper.toEntity(clienteGuardado1)));
        ClienteDTO clienteExiste1 = clienteService.buscarPorId(1);

        when(clienteRepository.findById(2)).thenReturn(Optional.ofNullable(ClienteMapper.toEntity(clienteGuardado2)));
        ClienteDTO clienteExiste2 = clienteService.buscarPorId(2);

        //------------------------------------------------------------------------

        assertNotNull(clienteExiste1);
        assertNotNull(clienteExiste2);

        assertNotEquals(clienteExiste1.getId(), clienteExiste2.getId());
        assertEquals(clienteExiste1.getId(), 1);
        assertEquals(clienteExiste2.getId(), 2);

        assertThrows(RecursoNoEncontradoExcepcion.class,() -> { clienteService.buscarPorId(2123); });
        assertThrows(RecursoNoEncontradoExcepcion.class,() -> { clienteService.buscarPorId(300); });

    }

    @Test
    void emailNoValido() {

        clienteDTO = new ClienteDTO();

        razonSocial = "Paulita SA";
        letra = "M";
        contacto = "Chacabuco 2000";
        celular = "+5493534899766";
        //mail = "juancitosa@hotmail.com";
        fechaInicio = new Date(2023, 2, 8);
        fechaNacimiento = new Date(2007, 9, 20);

        clienteDTO.setRazonSocial(razonSocial);
        clienteDTO.setLetra(letra);
        clienteDTO.setContacto(contacto);
        clienteDTO.setCelular(celular);
        //clienteDTO.setMail(mail);
        clienteDTO.setFechaInicio(fechaInicio);
        clienteDTO.setFechaNacimiento(fechaNacimiento);

        //------------------------------------------------------------------------

        clienteDTO.setMail("no_valid@ooo");

        clienteDTO.setId(3);
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        ClienteDTO clienteGuardado = clienteService.guardar(clienteDTO);

        String emailAddress = clienteGuardado.getMail();
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern emailValidation = Pattern.compile(regexPattern);
        assertFalse(emailValidation.matcher(emailAddress).matches());

        //------------------------------------------------------------------------

        clienteDTO.setMail("mail__@valido.com.ar");

        clienteDTO.setId(3);
        cliente = ClienteMapper.toEntity(clienteDTO);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        clienteGuardado = clienteService.guardar(clienteDTO);

        emailAddress = clienteGuardado.getMail();
        //regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        //emailValidation = Pattern.compile(regexPattern);
        assertTrue(emailValidation.matcher(emailAddress).matches());

    }

}
