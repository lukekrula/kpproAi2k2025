package cz.uhk.kppro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "legal_forms")
public class LegalForm {

    @Id
    private String id; // stejné jako extendedCode (ROSFORMA)

    @Indexed
    private String formCode; // FORMA (CHODNOTA z ref=2)

    private String formName; // TEXT z ref=2

    @Indexed(unique = true)
    private String extendedCode; // ROSFORMA (CHODNOTA z ref=1)

    private String extendedName; // TEXT z ref=1

    // Derived category (optional)
    private String category; // SPOLEK, FIRMA, NADACE, OBEC, INSTITUCE

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getExtendedCode() {
        return extendedCode;
    }

    public void setExtendedCode(String extendedCode) {
        this.extendedCode = extendedCode;
    }

    public String getExtendedName() {
        return extendedName;
    }

    public void setExtendedName(String extendedName) {
        this.extendedName = extendedName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
