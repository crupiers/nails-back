package jsges.nails.service.servicios;
import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.domain.servicios.TipoServicio;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.repository.servicios.TipoServicioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TipoServicioService implements ITipoServicioService {

    @Autowired
    private TipoServicioRepository modelRepository;
    private static final Logger logger = LoggerFactory.getLogger(TipoServicioService.class);

    @Override
    public List<TipoServicio> listar() {
        return modelRepository.buscarNoEliminados();
    }

    @Override
    public TipoServicio buscarPorId(Integer id) {

        TipoServicio model = modelRepository.findById(id).orElse(null);
        if(model == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);


        return model;
    }



    @Override
    public TipoServicio guardar(TipoServicioDTO model) {

        List<TipoServicio> list = this.buscar(model.denominacion);
        if (!list.isEmpty()){
            throw new NullPointerException();
            //return ResponseEntity.status(HttpStatus.CONFLICT).build();
            //throw new RecursoNoEncontradoExcepcion("Ya existe una linea con la denominacion: " + model.denominacion);
        }

        TipoServicio nuevoModelo = this.newModel(model);

        return modelRepository.save(nuevoModelo);
    }


    @Override
    public TipoServicio newModel(TipoServicioDTO modelDTO) {
        TipoServicio model =  new TipoServicio();
        model.setDenominacion(modelDTO.denominacion);
        return modelRepository.save(model);
    }


    @Override
    public Void eliminar(Integer id) {

        TipoServicio model = this.buscarPorId(id);
        if (model == null)
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);

        model.setEstado(1);

        modelRepository.save(model);
        return null;
    }

    @Override
    public List<TipoServicio> listar(String consulta) {
        //logger.info("service " +consulta);
        return modelRepository.buscarNoEliminados(consulta);
    }

    @Override
    public Page<TipoServicio> getTiposServicios(Pageable pageable) {
        return  modelRepository.findAll(pageable);
    }

    public List<TipoServicio> buscar(String consulta) {
        return modelRepository.buscarExacto(consulta);
    }



    @Override
    public Page<TipoServicio> findPaginated(Pageable pageable, String consulta) {

        List<TipoServicio> lineas = this.listar(consulta);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<TipoServicio> list;
        if (lineas.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, lineas.size());
            list = lineas.subList(startItem, toIndex);
        }

        Page<TipoServicio> bookPage
                = new PageImpl<TipoServicio>(list, PageRequest.of(currentPage, pageSize), lineas.size());

        return bookPage;
    }


    public TipoServicio actualizar(Integer id, TipoServicio modelRecibido) {
        TipoServicio model = this.buscarPorId(id);
        if (model == null)
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        return modelRepository.save(modelRecibido);
    }

}
