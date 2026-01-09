package cz.uhk.kppro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegistrationDto {

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Please repeat the password")
    private String repeatPassword;


    @NotBlank(message = "Organization type is required")
    private String organizationType;
    // values: "COMMUNITY", "PARTNER"



    private Long communityId;          // join existing community
    private String newCommunityName;   // create new community


    private String partnerName;
    private String partnerContactEmail;
    private String partnerContactPerson;


    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getNewCommunityName() {
        return newCommunityName;
    }

    public void setNewCommunityName(String newCommunityName) {
        this.newCommunityName = newCommunityName;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerContactEmail() {
        return partnerContactEmail;
    }

    public void setPartnerContactEmail(String partnerContactEmail) {
        this.partnerContactEmail = partnerContactEmail;
    }

    public String getPartnerContactPerson() {
        return partnerContactPerson;
    }

    public void setPartnerContactPerson(String partnerContactPerson) {
        this.partnerContactPerson = partnerContactPerson;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
