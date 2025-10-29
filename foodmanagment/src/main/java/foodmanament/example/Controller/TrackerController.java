package foodmanament.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import foodmanament.example.Entity.Tracker;
import foodmanament.example.Repository.TrackerRepository;
import java.util.List;

@RestController
@RequestMapping("/tracker")
public class TrackerController {

    @Autowired
    private TrackerRepository trackerRepository;

    // ➕ Add a new tracker entry
    @PostMapping("/add")
    public Tracker addTracker(@RequestBody Tracker tracker) {
        return trackerRepository.save(tracker);
    }

    // 🔍 Get all tracker details
    @GetMapping("/all")
    public List<Tracker> getAllTrackers() {
        return trackerRepository.findAll();
    }

    // 🔍 Get tracker by ID
    @GetMapping("/{id}")
    public Tracker getTrackerById(@PathVariable Long id) {
        return trackerRepository.findById(id).orElse(null);
    }

    // ✏️ Update tracker info (like delivery status)
    @PutMapping("/{id}")
    public Tracker updateTracker(@PathVariable Long id, @RequestBody Tracker updatedTracker) {
        return trackerRepository.findById(id)
                .map(tracker -> {
                    tracker.setStatus(updatedTracker.getStatus());
                    tracker.setLastUpdated(updatedTracker.getLastUpdated());
                    return trackerRepository.save(tracker);
                })
                .orElse(null);
    }

    // ❌ Delete tracker
    @DeleteMapping("/{id}")
    public String deleteTracker(@PathVariable Long id) {
        trackerRepository.deleteById(id);
        return "Tracker deleted successfully!";
    }
}
