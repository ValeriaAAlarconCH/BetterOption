package pe.edu.upc.grupo1_betteroption.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.grupo1_betteroption.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
