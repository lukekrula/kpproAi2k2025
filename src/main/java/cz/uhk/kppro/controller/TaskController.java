package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Task;
import cz.uhk.kppro.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }





        @PostMapping("/create-l")
        public String createTask(
                @RequestParam String name,
                @RequestParam(required = false, name = "subTasks") List<String> subTasks
        ) {
            Task task = taskService.createTask(name);

            if (subTasks != null) {
                subTasks.forEach(sub ->
                        taskService.addSubTask(task.getId(), sub)
                );
            }

            return "redirect:logged";
        }
    }
