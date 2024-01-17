package nl.novi.techiteasyopnieuw.repositories;

import nl.novi.techiteasyopnieuw.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
