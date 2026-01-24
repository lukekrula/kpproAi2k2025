package cz.uhk.kppro.dto;

import cz.uhk.kppro.model.LegalForm;
import cz.uhk.kppro.model.Organization;

public class OrganizationDetailDto {

    private String id;
    private String ico;
    private String name;

    private String legalFormCode;
    private String legalFormName;

    private String extendedLegalFormCode;
    private String extendedLegalFormName;

    private String category;

    // location, address, etc.


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getLegalFormCode() {
        return legalFormCode;
    }

    public void setLegalFormCode(String legalFormCode) {
        this.legalFormCode = legalFormCode;
    }

    public String getLegalFormName() {
        return legalFormName;
    }

    public void setLegalFormName(String legalFormName) {
        this.legalFormName = legalFormName;
    }

    public String getExtendedLegalFormCode() {
        return extendedLegalFormCode;
    }

    public void setExtendedLegalFormCode(String extendedLegalFormCode) {
        this.extendedLegalFormCode = extendedLegalFormCode;
    }

    public String getExtendedLegalFormName() {
        return extendedLegalFormName;
    }

    public void setExtendedLegalFormName(String extendedLegalFormName) {
        this.extendedLegalFormName = extendedLegalFormName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

