package pe.edu.upc.grupo1_betteroption.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDeseadoDto;
import pe.edu.upc.grupo1_betteroption.entities.Producto;
import pe.edu.upc.grupo1_betteroption.entities.Wishlist;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
    //Productos en wishlist de un usuario
    @Query("""
        SELECT p
        FROM Wishlist w
        JOIN w.productos p
        WHERE w.usuario.id_Usuario = :idUsuario
    """)
    List<Producto> findProductosEnWishlistPorUsuario(@Param("idUsuario") Long idUsuario);

    //Productos más deseados
    @Query("""
        SELECT new pe.edu.upc.grupo1_betteroption.dtos.ProductoDeseadoDto(p.nombreProducto, COUNT(p))
        FROM Wishlist w
        JOIN w.productos p
        GROUP BY p.id_Producto, p.nombreProducto
        ORDER BY COUNT(p) DESC
    """)
    List<ProductoDeseadoDto> findProductosMasDeseados();
}
