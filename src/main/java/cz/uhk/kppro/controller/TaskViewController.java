package cz.uhk.kppro.controller;

import org.springframework.ui.Model;
import cz.uhk.kppro.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskViewController {

    private final TaskService taskService;

    @Autowired
    public TaskViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    // List tasks
    @GetMapping
    public String taskList(Model model) {
        model.addAttribute("tasks", taskService.findAllRootTasks());
        return "user/tasks";   // templates/user/task.html
    }

    // Add task form
    @GetMapping("/add-task")
    public String addTaskForm() {
        return "user/add-task"; // templates/user/add-task.html
    }

    // Create task and redirect
    @PostMapping("/create")
    public String createTask(
            @RequestParam String name,
            @RequestParam(required = false) List<String> subTasks
    ) {
        var task = taskService.createTask(name);

        if (subTasks != null) {
            subTasks.forEach(sub -> taskService.addSubTask(task.getId(), sub));
        }

        // Redirect to task list page after creation
        return "redirect:/tasks";
    }
}