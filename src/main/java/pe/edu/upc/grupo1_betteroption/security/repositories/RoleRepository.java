package pe.edu.upc.grupo1_betteroption.security.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.grupo1_betteroption.security.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
