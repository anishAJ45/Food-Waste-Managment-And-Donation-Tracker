package foodmanament.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import foodmanament.example.Entity.Donor;
import foodmanament.example.Repository.DonorRepository;

@Controller
@RequestMapping("/donors")
@PreAuthorize("hasRole('ADMIN')")
public class DonorController {

    @Autowired
    private DonorRepository donorRepository;

    // ➕ Add new donor
    @PostMapping("/add")
    public String addDonor(@ModelAttribute Donor donor) {
        donorRepository.save(donor);
        return "redirect:/donors/all";
    }

    // ➕ Show add donor form
    @GetMapping("/add-form")
    public String showAddDonorForm(Model model) {
        model.addAttribute("donor", new Donor());
        return "add-donor";
    }

    // 🔍 Get all donors
    @GetMapping("/all")
    public String getAllDonors(Model model) {
        List<Donor> donors = donorRepository.findAll();
        model.addAttribute("donors", donors);
        return "donors";
    }

    // 🔍 Get donor by ID
    @GetMapping("/{id}")
    public Donor getDonorById(@PathVariable Long id) {
        return donorRepository.findById(id).orElse(null);
    }

    // ❌ Delete donor
    @DeleteMapping("/{id}")
    public String deleteDonor(@PathVariable Long id) {
        donorRepository.deleteById(id);
        return "Donor deleted successfully!";
    }
}
