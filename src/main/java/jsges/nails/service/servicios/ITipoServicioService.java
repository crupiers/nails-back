package jsges.nails.service.servicios;

import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.domain.servicios.TipoServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITipoServicioService {

    public List<TipoServicio> listar();

    public TipoServicio buscarPorId(Integer id);

    public TipoServicio guardar(TipoServicioDTO model);

    public Void eliminar(Integer id);

    public List<TipoServicio> listar(String consulta);

    public Page<TipoServicio> getTiposServicios(Pageable pageable);

   // public Page<TipoServicio> findPaginated(Pageable pageable,String consulta);


    public List<TipoServicio> buscar(String consulta);

    public TipoServicio newModel(TipoServicioDTO model);

    public TipoServicio actualizar(Integer id, TipoServicio modelRecibido);
}
