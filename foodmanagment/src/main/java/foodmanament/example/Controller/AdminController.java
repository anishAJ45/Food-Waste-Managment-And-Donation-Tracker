package foodmanament.example.Controller;

import foodmanament.example.Entity.Donation;
import foodmanament.example.Entity.Donor;
import foodmanament.example.Service.DonationService;
import foodmanament.example.Service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private DonorService donorService;

    @Autowired
    private DonationService donationService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        List<Donor> donors = donorService.getAllDonors();
        List<Donation> donations = donationService.getAllDonations();

        model.addAttribute("donors", donors);
        model.addAttribute("donations", donations);
        model.addAttribute("totalDonors", donors.size());
        model.addAttribute("totalDonations", donations.size());

        return "admin-dashboard";
    }
}
