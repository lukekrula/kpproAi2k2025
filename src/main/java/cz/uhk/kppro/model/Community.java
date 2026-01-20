package cz.uhk.kppro.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("COMMUNITY")
public class Community extends Organization {

    private String registrationNumber;
    private String caseNumber;
    private String foundingDate;
    private String foundingDateRaw;
    private String address;

    @OneToMany(mappedBy = "organization")
    private List<Membership> memberships;


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

    public String getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(String foundingDate) {
        this.foundingDate = foundingDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
