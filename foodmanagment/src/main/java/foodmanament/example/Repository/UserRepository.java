package foodmanament.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import foodmanament.example.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // This helps during login/authentication
    User findByEmail(String email);
}
