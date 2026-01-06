package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public int totalEstimated(List<Task> tasks) {
        return tasks.stream()
                .mapToInt(this::totalEstimated)
                .sum();
    }

    public int totalFinished(List<Task> tasks) {
        return tasks.stream()
                .mapToInt(this::totalFinished)
                .sum();
    }

}
