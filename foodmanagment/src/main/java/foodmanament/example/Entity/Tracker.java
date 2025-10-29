package foodmanament.example.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tracker")
public class Tracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary Key

    private String status;  //  Pending, Shipped, Delivered
    private LocalDateTime lastUpdated = LocalDateTime.now();

    
    @OneToOne   // One Tracker belongs to one Donation
    @JoinColumn(name = "donation_id")  // Foreign Key column in tracker table
    private Donation donation;

    @Override
    public String toString() {
        return "Tracker{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
