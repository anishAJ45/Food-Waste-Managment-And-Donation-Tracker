package foodmanament.example.Entity;


import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // Primary Key

    private String itemName;       // Clothes, Food, Money
    private int quantity;          // How many items
    private LocalDateTime donationDate = LocalDateTime.now(); // auto-filled

    // Many donations can be made by one user
    @ManyToOne
    @JoinColumn(name = "user_id")  // Foreign key column in donations table
    private User user;

    // One donation has one tracker
    @OneToOne(mappedBy = "donation", cascade = CascadeType.ALL)
    private Tracker tracker;

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", donationDate=" + donationDate +
                '}';
    }
}
