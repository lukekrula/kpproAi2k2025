package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Program;
import cz.uhk.kppro.model.Task;
import cz.uhk.kppro.service.TaskApplicationService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/programs/{programId}/tasks")
public class TaskViewController {

    private final TaskApplicationService taskApp;

    public TaskViewController(TaskApplicationService taskApp) {
        this.taskApp = taskApp;
    }



    @GetMapping("/{taskId}/subtasks")
    public String subtasksFragment(@PathVariable long programId,
                                   @PathVariable long taskId,
                                   @RequestParam(required = false) Boolean fragment,
                                   Model model) {

        model.addAttribute("tasks", taskApp.getSubtasks(taskId));
        model.addAttribute("programId", programId);
        model.addAttribute("level", 1);

        if (Boolean.TRUE.equals(fragment)) {
            return "fragments/task-subtasks :: subtaskRows";
        }

        return "programs/detail";
    }



    @GetMapping
    public String listTasks(@PathVariable long programId, Model model) {

        model.addAttribute("programId", programId);
        model.addAttribute("tasks", taskApp.getRootTasks(programId));

        return "tasks/list";
    }



    @GetMapping("/{taskId}")
    public String taskDetail(@PathVariable long taskId, Model model) {

        model.addAttribute("task", taskApp.getTask(taskId));
        return "tasks/detail";
    }



    @GetMapping("/create")
    public String createForm(@PathVariable long programId, Model model) {

        model.addAttribute("programId", programId);
        model.addAttribute(
                "members",
                taskApp.getAssignableMembers(programId)
        );

        return "tasks/create";
    }



    @PostMapping("/create")
    public String createTask(@PathVariable long programId,
                             @RequestParam long memberId,
                             @RequestParam String name,
                             @RequestParam(required = false) List<String> subTasks) {

        taskApp.createTaskWithSubtasks(programId, memberId, name, subTasks);
        return "redirect:/programs/" + programId;
    }



    @PostMapping("/{parentId}/subtask")
    public String createSubtask(@PathVariable long programId,
                                @PathVariable long parentId,
                                @RequestParam String name) {

        taskApp.addSubtask(parentId, name);
        return "redirect:/programs/" + programId;
    }



    @PostMapping("/{taskId}/complete")
    public String completeTask(@PathVariable long programId,
                               @PathVariable long taskId) {

        taskApp.completeTask(taskId);
        return "redirect:/programs/" + programId + "/tasks/" + taskId;
    }
}
