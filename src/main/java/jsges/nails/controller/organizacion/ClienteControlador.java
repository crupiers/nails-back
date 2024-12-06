package jsges.nails.controller.organizacion;

import jakarta.validation.Valid;
import jsges.nails.DTO.Organizacion.ClienteDTO;
import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.Linea;
import jsges.nails.domain.organizacion.Cliente;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.service.organizacion.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")

public class ClienteControlador {
    private static final Logger logger = LoggerFactory.getLogger(ClienteControlador.class);
    @Autowired
    private IClienteService clienteServicio;


    public ClienteControlador() {
    }

    @GetMapping({"/clientes"})
    public ResponseEntity<List<ClienteDTO>> getAll() {
        return ResponseEntity.ok(clienteServicio.listar());
    }

    /**
    @GetMapping({"/clientesPageQuery"})
    public ResponseEntity<Page<ClienteDTO>> getItems(@RequestParam(defaultValue = "") String consulta,@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "${max_page}") int size) {
        return ResponseEntity.ok().body(clienteServicio.findPaginated(PageRequest.of(page, size),consulta));
    }
     */


    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> agregar(@Valid @RequestBody  ClienteDTO cliente){
        return ResponseEntity.ok(clienteServicio.guardar(cliente));
    }


    @PutMapping("/clienteEliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        return ResponseEntity.ok(clienteServicio.eliminar(id));
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<ClienteDTO> getPorId(@PathVariable Integer id){
        return ResponseEntity.ok(clienteServicio.buscarPorId(id));
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Integer id,
                                                 @Valid @RequestBody  Cliente modelRecibido){
        return ResponseEntity.ok(clienteServicio.actualizar(id, modelRecibido));
    }

}
