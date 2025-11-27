package cz.uhk.kppro.model;
import java.util.List;
public class Metadata {
    private List<String> tags;
    private String visibility;
    // getters/setters

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
