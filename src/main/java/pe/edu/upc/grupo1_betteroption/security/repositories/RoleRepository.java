package pe.edu.upc.grupo1_betteroption.security.repositories;
import com.upc.products.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
