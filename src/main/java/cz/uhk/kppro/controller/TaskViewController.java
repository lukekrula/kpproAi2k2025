package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Program;
import cz.uhk.kppro.model.Task;
import cz.uhk.kppro.service.MemberService;
import cz.uhk.kppro.service.ProgramService;
import org.springframework.ui.Model;
import cz.uhk.kppro.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/programs/{programId}/tasks")
public class TaskViewController {

    private final TaskService taskService;
    private final ProgramService programService;
    private final MemberService memberService;

    public TaskViewController(TaskService taskService,
                              ProgramService programService,
                              MemberService memberService) {
        this.taskService = taskService;
        this.programService = programService;
        this.memberService = memberService;

    }
    @GetMapping("/programs/{pid}/tasks/{tid}/subtasks")
    public String subtasksFragment(@PathVariable Long pid,
                                   @PathVariable Long tid,
                                   @RequestParam(required = false) Boolean fragment,
                                   Model model) {

        Task parent = taskService.get(tid);
        List<Task> subtasks = parent.getSubTasks(); // or service call

        model.addAttribute("tasks", subtasks);
        model.addAttribute("program", programService.get(pid));
        model.addAttribute("level", 1); // or compute if you want

        if (Boolean.TRUE.equals(fragment)) {
            return "fragments/task-subtasks :: subtaskRows";
        }

        // non-fragment fallback if you ever need it
        return "programs/detail";
    }


    // List all tasks belonging to a program
    @GetMapping
    public String listTasks(@PathVariable long programId, Model model) {

        List<Task> all = taskService.getByProgram(programId);

        // Only top-level tasks (no parent)
        List<Task> roots = all.stream()
                .filter(t -> t.getParent() == null)
                .toList();

        model.addAttribute("programId", programId);
        model.addAttribute("tasks", roots);

        return "tasks/list";
    }


    @GetMapping("/{id}")
    public String taskDetail(@PathVariable long id, Model model) {
        Task task = taskService.get(id);
        model.addAttribute("task", task);
        return "tasks/detail";

    }

    @GetMapping("/create")
    public String createForm(@PathVariable long programId, Model model) {

        Program program = programService.get(programId);

        model.addAttribute("programId", programId);
        long communityId = program.getCreator().getId();
        List<Member> members = memberService.findByCommunity(communityId);

        model.addAttribute("members", members);


        return "tasks/create";
    }


    @PostMapping("/create")
    public String createTask(@PathVariable long programId,
                             @RequestParam long memberId,
                             @RequestParam String name,
                             @RequestParam(required = false) List<String> subTasks) {

        Task parent = new Task(name);
        Task savedParent = taskService.createTask(programId, memberId, parent);

        if (subTasks != null) {
            for (String subName : subTasks) {
                if (subName != null && !subName.trim().isEmpty()) {
                    taskService.addSubtask(savedParent.getId(), new Task(subName));
                }
            }
        }

        return "redirect:/programs/" + programId;
    }


    // Create a subtask under a parent task
    @PostMapping("/{parentId}/subtask")
    public String createSubtask(@PathVariable long programId,
                                @PathVariable long parentId,
                                @RequestParam String name) {

        taskService.addSubtask(parentId, new Task(name));
        return "redirect:/programs/" + programId;
    }

    @PostMapping("/{taskId}/complete")
    public String completeTask(@PathVariable long programId,
                               @PathVariable long taskId) {

        Task task = taskService.get(taskId);
        task.complete();
        taskService.update(task);

        return "redirect:/programs/" + programId + "/tasks/" + taskId;
    }

}
