package jsges.nails.service.servicios;
import jsges.nails.DTO.articulos.ArticuloVentaDTO;
import jsges.nails.DTO.servicios.ItemServicioDTO;
import jsges.nails.DTO.servicios.ServicioDTO;
import jsges.nails.domain.articulos.ArticuloVenta;
import jsges.nails.domain.servicios.ItemServicio;
import jsges.nails.domain.servicios.Servicio;
import jsges.nails.domain.servicios.TipoServicio;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.repository.servicios.ServicioRepository;
import jsges.nails.service.organizacion.IClienteService;
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
public class ServicioService implements IServicioService {

    @Autowired
    private ServicioRepository modelRepository;
    private static final Logger logger = LoggerFactory.getLogger(ServicioService.class);

    @Autowired
    private IItemServicioService itemServicioService;
    @Autowired
    private IClienteService clienteService;
    @Autowired
    private ITipoServicioService tipoServicioService;

    @Override
    public List<ServicioDTO> listar() {

        List<Servicio> servicios = modelRepository.buscarNoEliminados();
        List<ServicioDTO> lista =new ArrayList<>();
        for (Servicio elemento : servicios) {
            System.out.println("1"); // Debug: Verificar el contenido
            List<ItemServicio> items = itemServicioService.listar();
            System.out.println("3"); // Debug: Verificar el contenido
            ServicioDTO ser  = new ServicioDTO(elemento,items);
            lista.add(ser);
        }
        return lista;
    }

    @Override
    public ServicioDTO buscarPorId(Integer id) {

        logger.info("entra  en buscar servicio"  );
        Servicio model = modelRepository.findById(id).orElse(null);
        if(model == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);

        List<ItemServicio>listItems = itemServicioService.buscarPorServicio(model.getId());
        ServicioDTO modelDTO  = new ServicioDTO(model,listItems);
        logger.info(modelDTO.toString());
        return modelDTO;
    }

    @Override
    public Servicio guardar(ServicioDTO model) {

        Integer idCliente = model.cliente;

        Servicio newModel =  new Servicio();
        newModel.setCliente(clienteService.buscarPorId(idCliente));
        newModel.setFechaRegistro(model.fechaDocumento);
        newModel.setFechaRealizacion(model.fechaDocumento);
        newModel.setEstado(0);
        newModel.setTipo(tipoServicioService.buscarPorId(model.getTipoServicioId()));

        Servicio servicioGuardado= modelRepository.save(newModel);
        for (ItemServicioDTO elemento : model.listaItems) {
            double precio = elemento.getPrecio();
            logger.info("entra for");

            //TipoServicio tipoServicio = tipoServicioService.buscarPorId(elemento.getTipoServicioId());
            String observacion = elemento.getObservacion();
            ItemServicio item = new ItemServicio(newModel, precio,observacion);

            itemServicioService.guardar(item);

        }

        return servicioGuardado;
    }


    @Override
    public Page<Servicio> getServicios(Pageable pageable) {
        return  modelRepository.findAll(pageable);
    }



    @Override
    public Page<ServicioDTO> findPaginated(Pageable pageable, String consulta) {

        List<Servicio> listadoModel = this.listar(consulta);
        List<ServicioDTO> listadoDTO    =  new ArrayList<>();
        listadoModel.forEach((model) -> {
            listadoDTO.add(new ServicioDTO(model));
        });
        //Page<ServicioDTO> bookPage = modelService.findPaginated(PageRequest.of(page, size),listadoDTO);


        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ServicioDTO> list;
        if (listadoDTO.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listadoDTO.size());
            list = listadoDTO.subList(startItem, toIndex);
        }

        Page<ServicioDTO> bookPage
                = new PageImpl<ServicioDTO>(list, PageRequest.of(currentPage, pageSize), listadoDTO.size());

        return bookPage;
    }

    @Override
    public List<Servicio> listar(String consulta) {
        //logger.info("service " +consulta);
        return modelRepository.buscarNoEliminados(consulta);
    }

}
