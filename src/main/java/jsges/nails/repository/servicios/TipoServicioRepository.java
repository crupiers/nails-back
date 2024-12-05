package jsges.nails.repository.servicios;
import jsges.nails.domain.servicios.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoServicioRepository extends JpaRepository<TipoServicio, Integer> {

    @Query("select p from TipoServicio p  where p.estado=0 order by p.nombre")
    List<TipoServicio> buscarNoEliminados();


    @Query("SELECT p FROM TipoServicio p WHERE p.estado = 0 AND  p.nombre LIKE %:consulta% ORDER BY p.nombre")
    List<TipoServicio> buscarNoEliminados(@Param("consulta") String consulta);

    @Query("SELECT p FROM TipoServicio p WHERE p.estado = 0 AND  p.nombre LIKE:consulta ORDER BY p.nombre")
    List<TipoServicio> buscarExacto(@Param("consulta") String consulta);

    List<TipoServicio> findByNombreContaining(String consulta);
}
