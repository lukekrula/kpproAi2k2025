package cz.uhk.kppro.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Community extends Organization {

    @Column(name = "RegistrationNumber")
    private String registrationNumber;

    @Column(name = "CaseNumber")
    private String caseNumber;

    @Column(name = "FoundingDate")
    private String foundingDateRaw;

    @Column(name = "Address")
    private String address;

    @Transient
    private String foundingDate;



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
