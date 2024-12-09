package jsges.nails.service.organizacion;

import jsges.nails.DTO.Organizacion.ClienteDTO;
import jsges.nails.domain.organizacion.Cliente;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.mappers.ClienteMapper;
import jsges.nails.repository.organizacion.ClienteRepository;
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

@Service
public class ClienteService implements IClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);
    @Override
    public List<ClienteDTO> listar() {

        List<ClienteDTO> listadoDTO    =  new ArrayList<>();
        List<Cliente> list = clienteRepository.buscarNoEliminados();

        list.forEach((model) -> {
            listadoDTO.add(new ClienteDTO(model));
        });
        return listadoDTO;
    }

    @Override
    public ClienteDTO buscarPorId(Integer id) {

        Cliente cliente =  clienteRepository.findById(id).orElse(null);
        if(cliente == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);

        return ClienteMapper.toDTO(cliente);
    }

    @Override
    public ClienteDTO guardar(ClienteDTO cliente) {

        //PASAMOS DE DTO A ENTIDAD
        Cliente model = ClienteMapper.toEntity(cliente);

        return ClienteMapper.toDTO(clienteRepository.save(model));
    }

    @Override
    public Void eliminar(Integer id) {

        Cliente model = ClienteMapper.toEntity(this.buscarPorId(id));
        if (model == null)
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);

        model.setEstado(1);

        this.clienteRepository.save(model);

         // clienteRepository.save(model);
          return null;
    }

    @Override
    public List<Cliente> listar(String consulta) {
         return clienteRepository.buscarNoEliminados(consulta);
    }

    public Page<Cliente> getClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }


    public Page<ClienteDTO> findPaginated(Pageable pageable, String consulta) {

        List<Cliente> listado = this.listar(consulta);
        List<ClienteDTO> listadoDTO    =  new ArrayList<>();
        listado.forEach((model) -> {
            listadoDTO.add(new ClienteDTO(model));
        });

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ClienteDTO> list;
        if (listadoDTO.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listadoDTO.size());
            list = listadoDTO.subList(startItem, toIndex);
        }

        Page<ClienteDTO> bookPage
                = new PageImpl<ClienteDTO>(list, PageRequest.of(currentPage, pageSize), listadoDTO.size());

        return bookPage;
    }


    public ClienteDTO actualizar(Integer id, Cliente modelRecibido) {
        Cliente model = ClienteMapper.toEntity(this.buscarPorId(id));
        if (model == null)
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);

        return ClienteMapper.toDTO(clienteRepository.save(modelRecibido));
    }

    @Override
    public Cliente obtenerPorId(Integer id) {

        Cliente cliente =  clienteRepository.findById(id).orElse(null);
        if(cliente == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);

        return cliente;
    }

}
