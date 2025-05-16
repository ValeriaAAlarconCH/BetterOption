package pe.edu.upc.grupo1_betteroption.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDeseadoDto;
import pe.edu.upc.grupo1_betteroption.entities.Producto;
import pe.edu.upc.grupo1_betteroption.entities.Wishlist;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
    //Wishlist de usuario
    @Query("""
SELECT w.producto
FROM Wishlist w
WHERE w.usuario.idUsuario = :idUsuario

""")
    List<Producto> findProductosEnWishlistPorUsuario(@Param("idUsuario") Long idUsuario);



    //Productos mas deseados
    @Query("""
SELECT new pe.edu.upc.grupo1_betteroption.dtos.ProductoDeseadoDto(w.producto.nombreProducto, COUNT(w))
FROM Wishlist w
GROUP BY w.producto.idProducto, w.producto.nombreProducto
ORDER BY COUNT(w) DESC
""")
    List<ProductoDeseadoDto> findProductosMasDeseados();


}
