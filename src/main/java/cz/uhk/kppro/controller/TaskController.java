package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Task;
import cz.uhk.kppro.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Create a root task inside a program
    @PostMapping("/create")
    public Task createTask(
            @RequestParam long programId,
            @RequestParam long memberId,
            @RequestParam String name
    ) {
        Task task = new Task(name);
        return taskService.createTask(programId, memberId, task);
    }

    // Create a subtask
    @PostMapping("/{parentId}/subtask")
    public Task addSubtask(
            @PathVariable long parentId,
            @RequestParam String name
    ) {
        Task subtask = new Task(name);
        return taskService.addSubtask(parentId, subtask);
    }
}
