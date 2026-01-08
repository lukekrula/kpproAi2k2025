package cz.uhk.kppro.model;


import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Organization {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @jakarta.persistence.Id
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    private String name;

    @Enumerated(EnumType.STRING)
    private OrganizationType type;
}

