package pe.edu.upc.grupo1_betteroption.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.grupo1_betteroption.entities.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
}
