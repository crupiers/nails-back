package jsges.nails.service.articulos;

import jsges.nails.DTO.articulos.ArticuloVentaDTO;
import jsges.nails.domain.articulos.ArticuloVenta;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.mappers.ArticuloVentaMapper;
import jsges.nails.mappers.LineaMapper;
import jsges.nails.repository.articulos.ArticuloVentaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class ArticuloVentaService implements IArticuloVentaService{
    @Autowired
    private ArticuloVentaRepository modelRepository;
    private static final Logger logger = LoggerFactory.getLogger(ArticuloVentaService.class);

    @Autowired
    private ILineaService lineaService;


    @Override
    public List<ArticuloVentaDTO> listar() {
        logger.info("enta en  traer todas los articulos");
        List<ArticuloVenta> list = modelRepository.buscarNoEliminados();
        List<ArticuloVentaDTO> listadoDTO    =  new ArrayList<>();
        list.forEach((model) -> {
            listadoDTO.add(new ArticuloVentaDTO(model));
        });
        return listadoDTO;
    }

    @Override
    public ArticuloVentaDTO buscarPorId(Integer id) {


        ArticuloVenta articuloVenta = modelRepository.findById(id).orElse(null);
        if(articuloVenta == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
        ArticuloVentaDTO model = new ArticuloVentaDTO(articuloVenta);
        //return ResponseEntity.ok(model);

        return model;
    }

    @Override
    public ArticuloVentaDTO guardar(ArticuloVentaDTO model) {

        logger.info("entra" );

        Integer idLinea = model.linea;

        ArticuloVenta newModel =  new ArticuloVenta();
        newModel.setDenominacion(model.denominacion);
        newModel.setLinea(LineaMapper.toEntity(lineaService.buscarPorId(idLinea)));

        //ArticuloVenta modelSave= modelService.guardar(newModel);


        return ArticuloVentaMapper.toDTO(modelRepository.save(newModel));
    }

    @Override
    public Void eliminar(Integer id) {

        ArticuloVenta model = modelRepository.findById(id).orElse(null);
        //ArticuloVenta model = this.buscarPorId(id);
        if (model == null){
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }

        model.asEliminado();
        //modelService.guardar(model);
        modelRepository.save(model);
        return null;
    }

    @Override
    public List<ArticuloVenta> listar(String consulta) {
        //logger.info("service " +consulta);
        return modelRepository.buscarNoEliminados(consulta);
    }

    @Override
    public Page<ArticuloVenta> getArticulos(Pageable pageable) {
        return  modelRepository.findAll(pageable);
    }


    @Override
    public Page<ArticuloVentaDTO> findPaginated(Pageable pageable, String consulta) {

        List<ArticuloVenta> listadoConsulta = this.listar(consulta);
        List<ArticuloVentaDTO> listadoDTO    =  new ArrayList<>();
        listadoConsulta.forEach((model) -> {
            listadoDTO.add(new ArticuloVentaDTO(model));
        });

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ArticuloVentaDTO> list;
        if (listadoDTO.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listadoDTO.size());
            list = listadoDTO.subList(startItem, toIndex);
        }

        Page<ArticuloVentaDTO> bookPage
                = new PageImpl<ArticuloVentaDTO>(list, PageRequest.of(currentPage, pageSize), listadoDTO.size());



        return bookPage;
    }


public ArticuloVentaDTO actualizar(Integer id, ArticuloVentaDTO modelRecibido){

    logger.info("articulo " +modelRecibido);
    ArticuloVenta model = modelRepository.findById(id).orElse(null);
    logger.info("articulo " +model);
    if (model == null){
        throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
    }
    logger.info("articulo " +model);
    model.setDenominacion(modelRecibido.denominacion);
    model.setLinea(LineaMapper.toEntity(lineaService.buscarPorId(modelRecibido.linea)));
    //modelRepository.save(model);
    return   ArticuloVentaMapper.toDTO(modelRepository.save(model));
}

}
