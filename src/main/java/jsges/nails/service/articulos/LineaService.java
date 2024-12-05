package jsges.nails.service.articulos;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.Linea;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.repository.articulos.LineaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LineaService implements ILineaService {

    @Autowired
    private LineaRepository modelRepository;
    private static final Logger logger = LoggerFactory.getLogger(LineaService.class);

    @Override
    public List<LineaDTO> listar() {

        logger.info("enta en  traer todas las lineas");
        List<LineaDTO> listadoDTO    =  new ArrayList<>();
        List<Linea>  list    = modelRepository.buscarNoEliminados();
        list.forEach((model) -> {
            listadoDTO.add(new LineaDTO(model));
        });
        return listadoDTO;
    }

    @Override
    public Linea buscarPorId(Integer id) {

        Linea linea = modelRepository.findById(id).orElse(null);
        if(linea == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
            //return null;
        }
        //LineaDTO model = new LineaDTO(linea);

        return linea;
    }



    @Override
    public Linea guardar(Linea model) {
        return modelRepository.save(model);
    }


    @Override
    public Linea newModel(LineaDTO modelDTO) {

        List<Linea> list = this.buscar(modelDTO.denominacion);
        if (!list.isEmpty()){
            throw new NullPointerException();
            //return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Linea model =  new Linea(modelDTO);


        //Linea nuevaLinea = modelService.newModel(model);

        return guardar(model);
    }


    @Override
    public Void eliminar(Integer id) {

        Linea model = this.buscarPorId(id);


       // if (model == null){
         //   throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        //}

        model.asEliminado();
        //modelService.guardar(model);

        modelRepository.save(model);
        return null;
    }

    @Override
    public List<Linea> listar(String consulta) {
        //logger.info("service " +consulta);
        return modelRepository.buscarNoEliminados(consulta);
    }

    @Override
    public Page<Linea> getLineas(Pageable pageable) {
        return  modelRepository.findAll(pageable);
    }

    public List<Linea> buscar(String consulta) {
        return modelRepository.buscarExacto(consulta);
    }


    @Override
    public Page<LineaDTO> findPaginated(Pageable pageable, String consulta) {

        List<LineaDTO> listadoDTO    =  new ArrayList<>();
        List<Linea> listado = this.listar(consulta);
        listado.forEach((model) -> {
            listadoDTO.add(new LineaDTO(model));
        });

       // Page<LineaDTO> bookPage = modelService.findPaginated(PageRequest.of(page, size),listadoDTO);



        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<LineaDTO> list;
        if (listadoDTO.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listadoDTO.size());
            list = listadoDTO.subList(startItem, toIndex);
        }

        Page<LineaDTO> bookPage
                = new PageImpl<LineaDTO>(list, PageRequest.of(currentPage, pageSize), listadoDTO.size());

        return bookPage;
    }

    public LineaDTO actualizar(Integer id, LineaDTO modelRecibido){
        Linea model = this.buscarPorId(modelRecibido.id);
        //if (model == null){
        //    throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        //}
        model.setDenominacion(modelRecibido.denominacion);
        return new LineaDTO(this.guardar(model));

    }

}
