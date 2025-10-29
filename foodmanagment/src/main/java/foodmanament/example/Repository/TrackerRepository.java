package foodmanament.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import foodmanament.example.Entity.Tracker;

@Repository
public interface TrackerRepository extends JpaRepository<Tracker, Long> {
	
   
}
