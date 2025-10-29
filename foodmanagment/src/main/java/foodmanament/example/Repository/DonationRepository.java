package foodmanament.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import foodmanament.example.Entity.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    // Optional: Add custom queries later if needed
}
