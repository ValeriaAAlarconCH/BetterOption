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

//    // Filtrar por categoría (tabla Producto)
//    List<Producto> findByCategoria(Long idCategoria);

    // Filtrar por rango de precios (tabla Producto)
    List<Producto> findByPrecioBetween(Double precioMin, Double precioMax);


}
