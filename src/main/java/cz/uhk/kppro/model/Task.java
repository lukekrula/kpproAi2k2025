package cz.uhk.kppro.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private boolean completed;
    private String description;
    private int estimatedDays;
    private int estimatedHours;


    //  Task belongs to a Program
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    //  Task is assigned to one Member
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_member_id")
    private Member assignedTo;

    // CHILD → PARENT
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Task parent;

    //  PARENT → CHILDREN
    @OneToMany(
            mappedBy = "parent",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Task> subTasks = new ArrayList<>();

    protected Task() {
        // JPA
    }

    public Task(String name) {
        this.name = name;
    }



    public void addSubTask(Task subTask) {
        subTask.parent = this;
        subTasks.add(subTask);
    }

    public void complete() {
        this.completed = true;
    }

    public boolean isFullyCompleted() {
        return completed && subTasks.stream().allMatch(Task::isFullyCompleted);
    }



    public void setParent(Task parent) {
        this.parent = parent;
        if (parent != null && !parent.subTasks.contains(this)) {
            parent.subTasks.add(this);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }




    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }

    public Task getParent() {
        return parent;
    }

    public Program getProgram() {
        return program;
    }

    public Member getAssignedTo() {
        return assignedTo;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public void setAssignedTo(Member assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEstimatedDays() {
        return estimatedDays;
    }

    public void setEstimatedDays(int estimatedDays) {
        this.estimatedDays = estimatedDays;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(int estimatedHours) {
        this.estimatedHours = estimatedHours;
    }
}
