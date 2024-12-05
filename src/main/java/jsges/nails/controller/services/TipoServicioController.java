package jsges.nails.controller.services;


import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.domain.servicios.TipoServicio;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.service.servicios.ITipoServicioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class TipoServicioController {
    private static final Logger logger = LoggerFactory.getLogger(TipoServicioController.class);
    @Autowired
    private ITipoServicioService modelService;

    public TipoServicioController() {

    }

    @GetMapping({"/tiposServicios"})
    public ResponseEntity<List<TipoServicio>> getAll() {
        return ResponseEntity.ok(modelService.listar());
    }

    @GetMapping({"/tiposServiciosPageQuery"})
    public ResponseEntity<Page<TipoServicio>> getItems(@RequestParam(defaultValue = "") String consulta, @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "${max_page}") int size) {
        return ResponseEntity.ok().body(modelService.findPaginated(PageRequest.of(page, size),consulta));
    }


    @PostMapping("/tiposServicios")
    public  ResponseEntity<TipoServicio>  agregar(@RequestBody TipoServicioDTO model){
        return ResponseEntity.ok(modelService.guardar(model));
    }


    @PutMapping("/tipoServicioEliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        return ResponseEntity.ok(modelService.eliminar(id));
    }

    @GetMapping("/tiposServicios/{id}")
    public ResponseEntity<TipoServicio> getPorId(@PathVariable Integer id){
        return ResponseEntity.ok(modelService.buscarPorId(id));
    }

    @PutMapping("/tiposServicios/{id}")
    public ResponseEntity<TipoServicio> actualizar(@PathVariable Integer id,
                                                   @RequestBody TipoServicio modelRecibido){
      return ResponseEntity.ok(modelService.actualizar(id, modelRecibido));
    }

}

