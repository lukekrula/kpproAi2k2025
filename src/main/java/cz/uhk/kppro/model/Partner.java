package cz.uhk.kppro.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Partner extends Organization {
    private String contactEmail;

    private String contactPerson;


    @OneToMany(mappedBy = "partner")
    private List<Program> programs;

    @OneToMany(mappedBy = "partner")
    private List<Member> members;

    // getters and setters


    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

}
