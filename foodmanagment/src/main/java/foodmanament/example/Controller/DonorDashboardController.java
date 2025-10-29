package foodmanament.example.Controller;

import foodmanament.example.Entity.Donation;
import foodmanament.example.Entity.Donor;
import foodmanament.example.Service.DonationService;
import foodmanament.example.Service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/donor")
@PreAuthorize("hasRole('DONOR')")
public class DonorDashboardController {

    @Autowired
    private DonorService donorService;

    @Autowired
    private DonationService donationService;

    @GetMapping("/dashboard")
    public String donorDashboard(Authentication authentication, Model model) {
        String username = authentication.getName();
        Optional<Donor> donorOpt = donorService.findByUsername(username);

        if (donorOpt.isPresent()) {
            Donor donor = donorOpt.get();
            List<Donation> donations = donationService.getAllDonations().stream()
                    .filter(d -> d.getDonor().getId().equals(donor.getId()))
                    .toList();

            model.addAttribute("donor", donor);
            model.addAttribute("donations", donations);
            model.addAttribute("totalDonations", donations.size());
        }

        return "donor-dashboard";
    }
}
