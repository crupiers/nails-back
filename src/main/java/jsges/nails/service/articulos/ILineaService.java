package jsges.nails.service.articulos;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.ArticuloVenta;
import jsges.nails.domain.articulos.Linea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILineaService {

    public List<LineaDTO> listar();

    public LineaDTO buscarPorId(Integer id);

    public LineaDTO guardar(LineaDTO model);

    public Void eliminar(Integer id);

    public List<Linea> listar(String consulta);
    public Page<Linea> getLineas(Pageable pageable);

    public Page<LineaDTO> findPaginated(Pageable pageable,String consulta);


    public List<Linea> buscar(String consulta);

    //public Linea newModel(LineaDTO model);

    public LineaDTO actualizar(Integer id, LineaDTO modelRecibido);

    public Linea obtenerPorId(Integer id);


}
