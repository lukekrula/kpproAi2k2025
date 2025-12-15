package cz.uhk.kppro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PostalCode", length = 10, nullable = false)
    private String postalCode;

    @Column(name = "Town", length = 100, nullable = false)
    private String town;

    // Constructors
    public Town() {}

    public Town(String postalCode, String town) {
        this.postalCode = postalCode;
        this.town = town;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getTown() { return town; }
    public void setTown(String town) { this.town = town; }
}