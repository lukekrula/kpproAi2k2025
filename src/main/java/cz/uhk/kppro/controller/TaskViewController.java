package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Task;
import org.springframework.ui.Model;
import cz.uhk.kppro.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/programs/{programId}/tasks")
public class TaskViewController {

    private final TaskService taskService;

    public TaskViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    // List tasks for a program
    @GetMapping
    public String listTasks(
            @PathVariable long programId,
            Model model
    ) {
        model.addAttribute("programId", programId);
        model.addAttribute("tasks", taskService.getAll()); // you may filter by program later
        return "tasks/list";
    }

    // Show form
    @GetMapping("/create")
    public String createForm(@PathVariable long programId, Model model) {
        model.addAttribute("programId", programId);
        return "tasks/create";
    }

    // Create task
    @PostMapping("/create")
    public String createTask(
            @PathVariable long programId,
            @RequestParam long memberId,
            @RequestParam String name
    ) {
        Task task = new Task(name);
        taskService.createTask(programId, memberId, task);
        return "redirect:/programs/" + programId + "/tasks";
    }

    // Create subtask
    @PostMapping("/{parentId}/subtask")
    public String createSubtask(
            @PathVariable long programId,
            @PathVariable long parentId,
            @RequestParam String name
    ) {
        Task subtask = new Task(name);
        taskService.addSubtask(parentId, subtask);
        return "redirect:/programs/" + programId + "/tasks";
    }
}
