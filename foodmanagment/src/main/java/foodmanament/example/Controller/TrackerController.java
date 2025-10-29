package foodmanament.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import foodmanament.example.Entity.Tracker;
import foodmanament.example.Entity.Donation;
import foodmanament.example.Repository.TrackerRepository;
import foodmanament.example.Repository.DonationRepository;
import java.util.List;

@Controller
@RequestMapping("/tracker")
public class TrackerController {

    @Autowired
    private TrackerRepository trackerRepository;

    @Autowired
    private DonationRepository donationRepository;

    // ➕ Add a new tracker entry
    @PostMapping("/add")
    public String addTracker(@ModelAttribute Tracker tracker) {
        trackerRepository.save(tracker);
        return "redirect:/tracker/all";
    }

    // ➕ Show add tracker form
    @GetMapping("/add-form")
    public String showAddTrackerForm(Model model) {
        model.addAttribute("tracker", new Tracker());
        model.addAttribute("donations", donationRepository.findAll());
        return "add-tracker";
    }

    // 🔍 Get all tracker details
    @GetMapping("/all")
    public String getAllTrackers(Model model) {
        List<Tracker> trackers = trackerRepository.findAll();
        model.addAttribute("trackers", trackers);
        return "trackers";
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
