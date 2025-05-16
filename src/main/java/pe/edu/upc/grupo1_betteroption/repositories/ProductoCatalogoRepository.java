package pe.edu.upc.grupo1_betteroption.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.grupo1_betteroption.entities.Producto;
import pe.edu.upc.grupo1_betteroption.entities.ProductoCatalogo;

import java.util.List;

public interface ProductoCatalogoRepository extends JpaRepository<ProductoCatalogo, Long> {
    // Productos con promociones activas (ProductoCatalogo + CatalogoPromociones)
    @Query("""
SELECT pc.producto FROM ProductoCatalogo pc
JOIN pc.catalogoPromociones cp
WHERE CURRENT_DATE BETWEEN cp.fechaInicio AND cp.fechaFin
""")
    List<Producto> findProductosConPromocionesActivas();

}
