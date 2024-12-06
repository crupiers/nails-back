package jsges.nails.controller.articulos;

import jakarta.validation.Valid;
import jsges.nails.DTO.articulos.ArticuloVentaDTO;
import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.ArticuloVenta;
import jsges.nails.domain.articulos.Linea;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.service.articulos.IArticuloVentaService;
import jsges.nails.service.articulos.ILineaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class ArticuloVentaController {
    private static final Logger logger = LoggerFactory.getLogger(ArticuloVentaController.class);
    @Autowired
    private IArticuloVentaService  modelService;

    @Autowired
    private ILineaService lineaService;

    public ArticuloVentaController() {

    }

    @GetMapping({"/articulos"})
    public ResponseEntity<List<ArticuloVentaDTO>> getAll() {
        return ResponseEntity.ok(modelService.listar());
    }

    /**
    @GetMapping({"/articulosPageQuery"})
    public ResponseEntity<Page<ArticuloVentaDTO>> getItems(@RequestParam(defaultValue = "") String consulta, @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "${max_page}") int size) {
        return ResponseEntity.ok().body(modelService.findPaginated(PageRequest.of(page, size),consulta));
    }
     */


    @PostMapping("/articulos")
    public ResponseEntity<ArticuloVentaDTO> agregar(@Valid @RequestBody ArticuloVentaDTO model){
        return ResponseEntity.ok(modelService.guardar(model));
    }


    @DeleteMapping("/articuloEliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        return ResponseEntity.ok(modelService.eliminar(id));
    }

    @GetMapping("/articulos/{id}")
    public ResponseEntity<ArticuloVentaDTO> getPorId(@PathVariable Integer id){
        return ResponseEntity.ok(modelService.buscarPorId(id));
    }

    @PutMapping("/articulos/{id}")
    public ResponseEntity<ArticuloVentaDTO> actualizar(@PathVariable Integer id,
                                                       @Valid @RequestBody ArticuloVentaDTO modelRecibido){
      return ResponseEntity.ok(modelService.actualizar(id, modelRecibido));
    }

}

