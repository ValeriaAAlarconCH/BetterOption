package pe.edu.upc.grupo1_betteroption.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.grupo1_betteroption.entities.Producto;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Buscar por nombre (tabla Producto)
    List<Producto> findByNombreProducto(String nombre);

    // Filtrar por rango de precios (tabla Producto)
    List<Producto> findByPrecioBetween(Double precioMin, Double precioMax);

}
