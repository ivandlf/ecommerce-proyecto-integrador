package ecommerce.utn.ecommerce.jar.repository;

import ecommerce.utn.ecommerce.jar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
