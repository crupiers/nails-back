package jsges.nails.service.articulos;

import jsges.nails.DTO.articulos.ArticuloVentaDTO;
import jsges.nails.domain.articulos.ArticuloVenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArticuloVentaService {

    public List<ArticuloVentaDTO> listar();

    public ArticuloVentaDTO buscarPorId(Integer id);

    public ArticuloVentaDTO guardar(ArticuloVentaDTO model);

    public Void eliminar(Integer id);

    public List<ArticuloVenta> listar(String consulta);

    public Page<ArticuloVenta> getArticulos(Pageable pageable);

    public Page<ArticuloVentaDTO> findPaginated(Pageable pageable, String consulta);

    public ArticuloVentaDTO actualizar(Integer id, ArticuloVentaDTO modelRecibido);

}
