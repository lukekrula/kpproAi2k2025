package cz.uhk.kppro.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "org_type")
public abstract class Organization {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "org_type", insertable = false, updatable = false)
    private OrganizationType type;

    public OrganizationType getType() {
        return type;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }
}
