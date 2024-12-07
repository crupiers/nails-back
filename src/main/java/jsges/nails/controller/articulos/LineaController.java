package jsges.nails.controller.articulos;
import jakarta.validation.Valid;
import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.Linea;
import jsges.nails.domain.organizacion.Cliente;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.service.articulos.ILineaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class LineaController {
    private static final Logger logger = LoggerFactory.getLogger(LineaController.class);
    @Autowired
    private ILineaService modelService;

    public LineaController() {

    }

    @GetMapping({"/lineas"})
    public ResponseEntity<List<LineaDTO>> getAll() {
       return ResponseEntity.ok(modelService.listar());
    }



    @GetMapping({"/lineasPageQuery"})
    public ResponseEntity<Page<LineaDTO>> getItems(@RequestParam(defaultValue = "") String consulta, @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "${max_page}") int size) {
        return ResponseEntity.ok().body(modelService.findPaginated(PageRequest.of(page, size),consulta));
    }



    @PostMapping("/linea")
    public  ResponseEntity<LineaDTO> agregar( @Valid @RequestBody  LineaDTO model){
        return ResponseEntity.ok(modelService.guardar(model));
    }

    @DeleteMapping("/lineaEliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        return ResponseEntity.ok(modelService.eliminar(id));
    }

    @GetMapping("/linea/{id}")
    public ResponseEntity<LineaDTO> getPorId(@PathVariable Integer id){
        return ResponseEntity.ok(modelService.buscarPorId(id));
    }

    @PutMapping("/linea/{id}")
    public ResponseEntity<LineaDTO> actualizar(@PathVariable Integer id,
                                               @Valid @RequestBody  LineaDTO modelRecibido){
        return ResponseEntity.ok(modelService.actualizar(id, modelRecibido));
    }

}

