package foodmanament.example.Entity;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties.Decryption;

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
@Table(name = "donations")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary Key

    private String name;
    private String email;
    private String password;
    private String contactNo;
    private LocalDateTime joinDate = LocalDateTime.now();

    // Relationship: One user can make many donations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Decryption> donations;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", joinDate=" + joinDate +
                '}';
    }
}
