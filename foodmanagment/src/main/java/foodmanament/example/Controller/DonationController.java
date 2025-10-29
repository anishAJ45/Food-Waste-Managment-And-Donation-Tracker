package foodmanament.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import foodmanament.example.Entity.Donation;
import foodmanament.example.Repository.DonationRepository;

@RestController
@RequestMapping("/donation")
public class DonationController {

    @Autowired
    private DonationRepository donationRepository;

    // Create a new donation
    @PostMapping("/add")
    public Donation addDonation(@RequestBody Donation donation) {
        return donationRepository.save(donation);
    }

    // Get all donations
    @GetMapping("/all")
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    // Get donation by ID
    @GetMapping("/{id}")
    public Donation getDonationById(@PathVariable Long id) {
        return donationRepository.findById(id).orElse(null);
    }

    // Update a donation (only itemName and quantity for simplicity)
    @PutMapping("/{id}")
    public Donation updateDonation(@PathVariable Long id, @RequestBody Donation updatedDonation) {
        return donationRepository.findById(id)
                .map(donation -> {
                    donation.setItemName(updatedDonation.getItemName());
                    donation.setQuantity(updatedDonation.getQuantity());
                    // donationDate, user and tracker are left unchanged here
                    return donationRepository.save(donation);
                })
                .orElse(null);
    }

    // Delete donation
    @DeleteMapping("/{id}")
    public String deleteDonation(@PathVariable Long id) {
        donationRepository.deleteById(id);
        return "Donation deleted successfully!";
    }
}
