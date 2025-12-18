package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Program;
import cz.uhk.kppro.service.ProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/programs")
public class ProgramViewController {

    private final ProgramService programService;

    public ProgramViewController(ProgramService programService) {
        this.programService = programService;
    }

    // List all programs
    @GetMapping
    public String listPrograms(Model model) {
        model.addAttribute("programs", programService.getAll());
        return "programs/list";
    }

    // Show create form
    @GetMapping("/create")
    public String createForm() {
        return "programs/create";
    }

    // Handle create
    @PostMapping("/create")
    public String createProgram(
            @RequestParam long managerId,
            @RequestParam long communityId,
            @RequestParam String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer amountMax
    ) {
        Program program = new Program();
        program.setName(name);
        program.setDescription(description);
        program.setAmountMax(amountMax);

        programService.createProgram(managerId, communityId, program);

        return "redirect:/programs";
    }

    // Program detail page
    @GetMapping("/{id}")
    public String programDetail(@PathVariable long id, Model model) {
        Program program = programService.get(id);
        model.addAttribute("program", program);
        model.addAttribute("tasks", program.getTasks());
        return "programs/detail";
    }
}

