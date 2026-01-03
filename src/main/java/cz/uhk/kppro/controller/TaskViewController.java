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


    // List all tasks belonging to a program
    @GetMapping
    public String listTasks(@PathVariable long programId, Model model) {
        model.addAttribute("programId", programId);
        model.addAttribute("tasks", taskService.getByProgram(programId));
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
                             @RequestParam String name) {

        taskService.createTask(programId, memberId, new Task(name));
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
}
