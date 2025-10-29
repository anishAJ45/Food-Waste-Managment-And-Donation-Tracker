package foodmanament.example.Service;

import foodmanament.example.Entity.Donation;
import foodmanament.example.Repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    // ➕ Add a new donation
    public Donation addDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    // 🔍 Get all donations
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    // 🔍 Get a donation by ID
    public Optional<Donation> getDonationById(Long id) {
        return donationRepository.findById(id);
    }

    // ✏️ Update donation details
    public Donation updateDonation(Long id, Donation updatedDonation) {
        return donationRepository.findById(id)
                .map(donation -> {
                    // Update fields that exist on the Donation entity
                    donation.setItemName(updatedDonation.getItemName());
                    donation.setQuantity(updatedDonation.getQuantity());
                    // user and tracker relationships are left unchanged here
                    return donationRepository.save(donation);
                })
                .orElse(null);
    }

    // ❌ Delete a donation
    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }
}
