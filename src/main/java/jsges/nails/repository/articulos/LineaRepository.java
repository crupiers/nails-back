package jsges.nails.repository.articulos;

import jsges.nails.domain.articulos.Linea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LineaRepository extends JpaRepository<Linea, Integer> {

    @Query("select p from Linea p  where p.estado=0 order by p.nombre")
    List<Linea> buscarNoEliminados();


    @Query("SELECT p FROM Linea p WHERE p.estado = 0 AND  p.nombre LIKE %:consulta% ORDER BY p.nombre")
    List<Linea> buscarNoEliminados(@Param("consulta") String consulta);

    @Query("SELECT p FROM Linea p WHERE p.estado = 0 AND  p.nombre LIKE:consulta ORDER BY p.nombre")
    List<Linea> buscarExacto(@Param("consulta") String consulta);

    List<Linea> findByNombreContaining(String consulta);
}
