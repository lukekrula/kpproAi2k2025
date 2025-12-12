package cz.uhk.kppro.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "community")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "communities")
    private List<Member> members;

    @ManyToMany(mappedBy = "communities")
    private List<Activity> activities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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


    public void setMembers(List<Member> members) {
        this.members = members;
    }


    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @Getter
    @ManyToMany(mappedBy = "communities")
    public List<Partner> partners = new ArrayList<>();

    public void setPartners(List<Partner> partners) {
        this.partners = partners;
    }

    public List<Member> getMembersList() {
    return members;
    }

    public List<Activity> getActivityList() {
    return activities;
    }

    public List<Partner> getPartnerList() {
    return partners;
    }
}
