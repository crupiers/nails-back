package jsges.nails.service.articulos;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.Linea;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.mappers.LineaMapper;
import jsges.nails.repository.articulos.LineaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    public LineaDTO buscarPorId(Integer id) {

        Linea linea = modelRepository.findById(id).orElse(null);
        if(linea == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
            //return null;
        }
        //LineaDTO model = new LineaDTO(linea);

        return LineaMapper.toDTO(linea);
    }




    @Override
    public LineaDTO guardar(LineaDTO modelDTO) {

        List<Linea> list = this.buscar(modelDTO.denominacion);
        if (!list.isEmpty()){
            throw new NullPointerException();
            //return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Linea model =  LineaMapper.toEntity(modelDTO);


        //Linea nuevaLinea = modelService.newModel(model);

        return LineaMapper.toDTO(modelRepository.save(model));
    }


    @Override
    public Void eliminar(Integer id) {

        Linea model = LineaMapper.toEntity(this.buscarPorId(id));


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
        int idRecibido = modelRecibido.id;
        Linea model = LineaMapper.toEntity(this.buscarPorId(modelRecibido.id));
        if (model == null || !Objects.equals(idRecibido, modelRecibido.id)){
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }
        return new LineaDTO(modelRepository.save(LineaMapper.toEntity(modelRecibido)));
    }

    @Override
    public Linea obtenerPorId(Integer id) {
        Linea linea = modelRepository.findById(id).orElse(null);
        if(linea == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
            //return null;
        }
        //LineaDTO model = new LineaDTO(linea);

        return linea;
    }
}
