package eu.tcitsolutions.dietApp.security.domain.repository;

import eu.tcitsolutions.dietApp.security.domain.entity.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<LoginUser, Long> {
    LoginUser findByUsername(String username);
}
