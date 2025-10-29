package foodmanament.example.Service;

import foodmanament.example.Entity.Donor;
import foodmanament.example.Repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ➕ Add a new donor
    public Donor addDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    // 🔍 Get all donors
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    // 🔍 Get donor by ID
    public Optional<Donor> getDonorById(Long id) {
        return donorRepository.findById(id);
    }

    // 🔍 Get donor by username
    public Optional<Donor> findByUsername(String username) {
        return donorRepository.findByUsername(username);
    }

    // 🔍 Get donor by email
    public Optional<Donor> findByEmail(String email) {
        return donorRepository.findByEmail(email);
    }

    // ✏️ Update donor
    public Donor updateDonor(Long id, Donor updatedDonor) {
        return donorRepository.findById(id)
                .map(donor -> {
                    donor.setUsername(updatedDonor.getUsername());
                    donor.setEmail(updatedDonor.getEmail());
                    donor.setPassword(updatedDonor.getPassword());
                    donor.setContactNo(updatedDonor.getContactNo());
                    return donorRepository.save(donor);
                })
                .orElse(null);
    }

    // ❌ Delete donor
    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }

    // Register a new donor (for authentication)
    public Donor registerDonor(Donor donor) {
        if (donorRepository.existsByUsername(donor.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (donorRepository.existsByEmail(donor.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        donor.setPassword(passwordEncoder.encode(donor.getPassword()));
        donor.setRole("DONOR"); // Ensure default role
        return donorRepository.save(donor);
    }
}
