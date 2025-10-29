package foodmanament.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import foodmanament.example.Entity.Donation;
import foodmanament.example.Entity.Donor;
import foodmanament.example.Repository.DonationRepository;
import foodmanament.example.Repository.DonorRepository;
import foodmanament.example.Service.DonorService;

@Controller
@RequestMapping("/donation")
@PreAuthorize("hasRole('ADMIN') or hasRole('DONOR')")
public class DonationController {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private DonorService donorService;

    // Create a new donation
    @PostMapping("/add")
    public String addDonation(@ModelAttribute Donation donation, Authentication authentication) {
        String username = authentication.getName();
        Donor donor = donorService.findByUsername(username).orElseThrow();
        donation.setDonor(donor);
        donationRepository.save(donation);
        return "redirect:/donation/all";
    }

    // ➕ Show add donation form
    @GetMapping("/add-form")
    public String showAddDonationForm(Model model) {
        model.addAttribute("donation", new Donation());
        model.addAttribute("donors", donorRepository.findAll());
        return "add-donation";
    }

    // Get all donations
    @GetMapping("/all")
    public String getAllDonations(Model model, Authentication authentication) {
        List<Donation> donations;
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            donations = donationRepository.findAll();
        } else {
            String username = authentication.getName();
            Donor donor = donorService.findByUsername(username).orElseThrow();
            donations = donationRepository.findAll().stream()
                    .filter(d -> d.getDonor().getId().equals(donor.getId()))
                    .toList();
        }
        model.addAttribute("donations", donations);
        return "donations";
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
