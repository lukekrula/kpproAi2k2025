package cz.uhk.kppro.model;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private LocalDate date;
    // getters/setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    @ManyToMany
    @JoinTable(
            name = "activity_community",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "community_id")
    )
    private List<Community> communities;

}
