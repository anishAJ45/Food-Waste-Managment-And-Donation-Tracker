package foodmanament.example.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Table(name = "donors")
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary Key

    private String username;
    private String email;
    private String password;
    private String contactNo;
    private LocalDateTime joinDate = LocalDateTime.now();
    private String role = "DONOR"; // Default role
    private boolean enabled = true;

    // Relationship: One donor can make many donations
    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Donation> donations;

    @Override
    public String toString() {
        return "Donor{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", joinDate=" + joinDate +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
