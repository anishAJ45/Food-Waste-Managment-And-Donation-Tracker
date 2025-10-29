package foodmanament.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import foodmanament.example.Entity.Donor;

import java.util.Optional;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {

    // This helps during login/authentication

    Optional<Donor> findByUsername(String username);

    Optional<Donor> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
