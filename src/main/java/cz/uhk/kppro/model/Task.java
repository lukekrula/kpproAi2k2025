package cz.uhk.kppro.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private boolean completed;

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

    // ===== Domain logic =====

    public void addSubTask(Task subTask) {
        subTask.parent = this;
        subTasks.add(subTask);
    }

    public void complete() {
        this.completed = true;
    }

    public boolean isCompleted() {
        return completed && subTasks.stream().allMatch(Task::isCompleted);
    }



    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCompletedFlag() {
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
}
