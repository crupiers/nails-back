package jsges.nails.service.organizacion;

import jsges.nails.DTO.Organizacion.ClienteDTO;
import jsges.nails.domain.organizacion.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {
    public List<ClienteDTO> listar();

    public Cliente buscarPorId(Integer id);

    public Cliente guardar(Cliente cliente);

    public Void eliminar(Integer id);

      public List<Cliente> listar(String consulta);

    public Page<Cliente> getClientes(Pageable pageable);

    public Page<ClienteDTO> findPaginated(Pageable pageable, String consulta);

    public Cliente actualizar(Integer id, Cliente modelRecibido);

}
