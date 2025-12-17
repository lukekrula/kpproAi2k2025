package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Task;
import cz.uhk.kppro.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public Task create(@RequestParam String name) {
        return service.createTask(name);
    }

    @PostMapping("/{id}/subtask")
    public void addSubTask(@PathVariable UUID id,
                           @RequestParam String name) {
        service.addSubTask(id, name);
    }

    @PostMapping("/{id}/complete")
    public void complete(@PathVariable UUID id) {
        service.completeTask(id);
    }

    @GetMapping
    public List<Task> roots() {
        return service.findAllRootTasks();
    }
}
