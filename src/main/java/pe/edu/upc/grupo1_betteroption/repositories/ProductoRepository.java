package pe.edu.upc.grupo1_betteroption.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDeseadoDto;
import pe.edu.upc.grupo1_betteroption.entities.Producto;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Buscar por nombre (tabla Producto)
    List<Producto> findByNombreProductoContainingIgnoreCase(String nombre);

    // Filtrar por categoría (tabla Producto)
    List<Producto> findByCategoria_Id_categoria(Long idCategoria);

    // Filtrar por rango de precios (tabla Producto)
    List<Producto> findByPrecioBetween(Double precioMin, Double precioMax);

    // Productos con promociones activas (ProductoCatalogo + CatalogoPromociones)
    @Query("""
    SELECT p FROM Producto p
    JOIN ProductoCatalogo pc ON p.id_producto = pc.producto.id_producto
    JOIN CatalogoPromociones cp ON pc.catalogoPromociones.id_catalogopromociones = cp.id_catalogopromociones
    WHERE CURRENT_DATE BETWEEN cp.fechaInicio AND cp.fechaFin
    """)
    List<Producto> findProductosConPromocionesActivas();

    // Wishlist del usuario (Wishlist)
    @Query("""
   SELECT p FROM Producto p
   JOIN Wishlist w ON p.id_producto = w.producto.id_producto
   WHERE w.usuario.id_usuario = :idUsuario
   """)
    List<Producto> findWishlistByUsuario(@Param("idUsuario") Long idUsuario);

    // Productos más deseados (Wishlist)
    @Query("""
    SELECT new pe.edu.upc.grupo1_betteroption.dtos.ProductoDeseadoDto(p.nombreProducto, COUNT(w)) 
    FROM Wishlist w
    JOIN w.producto p
    GROUP BY p.id_producto, p.nombreProducto
    ORDER BY COUNT(w) DESC
    """)
    List<ProductoDeseadoDto> findProductosMasDeseados();
}
