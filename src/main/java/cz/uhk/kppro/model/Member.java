package cz.uhk.kppro.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    // NEW: A member can belong to many organizations (Community, Partner, etc.)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Membership> memberships = new HashSet<>();

    // These stay if you still want them:
    @ManyToMany(mappedBy = "assignedMembers")
    private Set<Program> assignedPrograms = new HashSet<>();

    @OneToMany(mappedBy = "assignedTo")
    private Set<Task> assignedTasks = new HashSet<>();

    @OneToMany(mappedBy = "manager")
    private Set<Program> managedPrograms = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;


    // Getters & setters...

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Set<Membership> getMemberships() { return memberships; }
    public void setMemberships(Set<Membership> memberships) { this.memberships = memberships; }
}
