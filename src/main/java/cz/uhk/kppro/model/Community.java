package cz.uhk.kppro.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "communities")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "RegistrationNumber")
    private String registrationNumber;

    @Column(name = "CaseNumber")
    private String caseNumber;

    @Column(name = "FoundingDate")
    private String foundingDateRaw;

    @Column(name = "Address")
    private String address;

    @Transient
    private LocalDate foundingDate;

    @ManyToMany(mappedBy = "communities")
    private List<Member> members = new ArrayList<>();

    @ManyToMany(mappedBy = "communities")
    private List<Activity> activities = new ArrayList<>();

    @ManyToMany(mappedBy = "communities")
    private List<Partner> partners = new ArrayList<>();

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

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getFoundingDateRaw() {
        return foundingDateRaw;
    }

    public void setFoundingDateRaw(String foundingDateRaw) {
        this.foundingDateRaw = foundingDateRaw;
    }

    public LocalDate getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(LocalDate foundingDate) {
        this.foundingDate = foundingDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Partner> getPartners() {
        return partners;
    }

    public void setPartners(List<Partner> partners) {
        this.partners = partners;
    }
}
