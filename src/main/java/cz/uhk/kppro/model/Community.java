package cz.uhk.kppro.model;

import jakarta.persistence.ManyToMany;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "communities")
public class Community {
    @Id
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public LocalDate getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(LocalDate foundingDate) {
        this.foundingDate = foundingDate;
    }

    private String idNumber;
    private LocalDate foundingDate;

    @Getter
    @ManyToMany(mappedBy = "community")
    private List<Member> members = new ArrayList<>();

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    @Getter
    @ManyToMany(mappedBy = "community")
    public List<Activity> activities = new ArrayList<>();

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @Getter
    @ManyToMany(mappedBy = "community")
    public List<Partner> partners = new ArrayList<>();

    public void setPartners(List<Partner> partners) {
        this.partners = partners;
    }

}
