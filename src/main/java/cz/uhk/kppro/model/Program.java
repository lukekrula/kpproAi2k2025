package cz.uhk.kppro.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "program")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private Integer amountMax;
    private String description;


    @ManyToOne
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "creator_community_id")
    private Community creator;

    @ManyToMany
    @JoinTable(
            name = "program_shared_communities",
            joinColumns = @JoinColumn(name = "program_id"),
            inverseJoinColumns = @JoinColumn(name = "community_id")
    )
    private List<Community> sharedWith = new ArrayList<>();

    // members assigned to this program
    @ManyToMany
    @JoinTable(
            name = "program_members",
            joinColumns = @JoinColumn(name = "program_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> assignedMembers = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Member manager;


    // Tasks belonging to this program
    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();



    public Community getCreator() {
        return creator;
    }

    public void setCreator(Community creator) {
        this.creator = creator;
    }

    public List<Community> getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(List<Community> sharedWith) {
        this.sharedWith = sharedWith;
    }

    public List<Member> getAssignedMembers() {
        return assignedMembers;
    }

    public void setAssignedMembers(List<Member> assignedMembers) {
        this.assignedMembers = assignedMembers;
    }

    public Member getManager() {
        return manager;
    }

    public void setManager(Member manager) {
        this.manager = manager;
    }

    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountMax() {
        return amountMax;
    }

    public void setAmountMax(Integer amountMax) {
        this.amountMax = amountMax;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
