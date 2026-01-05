package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskCalculationService {

    public int estimatedHours(Task task) {
        int days = task.getEstimatedDays();
        int hours = task.getEstimatedHours();

        if (days == 0 && hours == 0) {
            return 8;
        }
        return days * 8 + hours;
    }

    public int getFinishedDays(Task task) {
        if (task.isCompleted()) {
            return task.getEstimatedDays();
        }else  {
            return 0;
        }
    }

    public int getFinishedHours(Task task) {
        if (task.isCompleted()) {
            return task.getEstimatedHours();
        }else  {
            return 0;
        }
    }

    public int finishedHours(Task task) {
        if (task.isCompleted()) {
            return estimatedHours(task);
        }
        return getFinishedDays(task) * 8 + getFinishedHours(task);
    }

    public int totalEstimated(Task task) {
        int total = estimatedHours(task);
        for (Task sub : task.getSubTasks()) {
            total += totalEstimated(sub);
        }
        return total;
    }

    public int totalFinished(Task task) {
        int total = finishedHours(task);
        for (Task sub : task.getSubTasks()) {
            total += totalFinished(sub);
        }
        return total;
    }

    public double completionPercentage(Task task) {
        int est = totalEstimated(task);
        int fin = totalFinished(task);

        if (est == 0) return 100;

        double pct = (double) fin / est * 100;
        return Math.min(100, Math.max(0, pct));
    }

    //  public int totalFinishedForMember(Task task, Member member) {
    //     return totalFinishedForMember(task, member, false);}

    public int totalFinishedForMember(Task task, Member member) {

        // Only count this task if it is assigned to the given member
        int total = (member.equals(task.getAssignedTo()))
                ? finishedHours(task)
                : 0;

        // Recursively add all subtasks
        for (Task sub : task.getSubTasks()) {
            total += totalFinishedForMember(sub, member);
        }

        return total;
    }


    private int totalFinishedForMember(Task task, Member member, boolean parentAssigned) {

        boolean assignedHere = member.equals(task.getAssignedTo());
        boolean effectiveAssignment = parentAssigned || assignedHere;

        int total = effectiveAssignment ? finishedHours(task) : 0;

        for (Task sub : task.getSubTasks()) {
            total += totalFinishedForMember(sub, member, effectiveAssignment);
        }

        return total;
    }

    public int totalEstimatedForMember(Task task, Member member) {
        int total = (member.equals(task.getAssignedTo())) ? estimatedHours(task) : 0;
        for (Task sub : task.getSubTasks()) {
            total += totalEstimatedForMember(sub, member);
        }
        return total;
    }
}
