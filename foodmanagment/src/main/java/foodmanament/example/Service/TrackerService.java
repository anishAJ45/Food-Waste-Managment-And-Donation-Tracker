package foodmanament.example.Service;

import foodmanament.example.Entity.Tracker;
import foodmanament.example.Repository.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackerService {

    @Autowired
    private TrackerRepository trackerRepository;

    // ➕ Add a new tracking record
    public Tracker addTracker(Tracker tracker) {
        return trackerRepository.save(tracker);
    }

    // 🔍 Get all tracking records
    public List<Tracker> getAllTrackers() {
        return trackerRepository.findAll();
    }

    // 🔍 Get tracker by ID
    public Optional<Tracker> getTrackerById(Long id) {
        return trackerRepository.findById(id);
    }

    // ✏️ Update tracking information
    public Tracker updateTracker(Long id, Tracker updatedTracker) {
        return trackerRepository.findById(id)
                .map(tracker -> {
                    tracker.setStatus(updatedTracker.getStatus());
                    tracker.setLastUpdated(updatedTracker.getLastUpdated());
                    tracker.setDonation(updatedTracker.getDonation());
                    return trackerRepository.save(tracker);
                })
                .orElse(null);
    }

    // ❌ Delete tracking record
    public void deleteTracker(Long id) {
        trackerRepository.deleteById(id);
    }
}
