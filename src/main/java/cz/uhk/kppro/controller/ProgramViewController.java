package cz.uhk.kppro.controller;

import cz.uhk.kppro.service.ProgramApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/programs")
public class ProgramViewController {

    private final ProgramApplicationService programApplicationService;

    public ProgramViewController(ProgramApplicationService programApplicationService) {
        this.programApplicationService = programApplicationService;
    }


    @GetMapping
    public String listPrograms(Model model) {
        model.addAttribute("programs", programApplicationService.getPrograms());
        return "programs/programs";
    }

    @GetMapping("/fragment")
    public String programListFragment(Model model) {
        model.addAttribute("programs", programApplicationService.getPrograms());
        return "programs/_program-list :: programList";
    }



    @GetMapping("/create")
    public String createForm() {
        return "new";
    }

    @PostMapping("/create")
    public String createProgram(@RequestParam long managerId,
                                @RequestParam long communityId,
                                @RequestParam String name,
                                @RequestParam(required = false) String description,
                                @RequestParam(required = false) Integer amountMax) {

        programApplicationService.createProgram(
                managerId,
                communityId,
                name,
                description,
                amountMax
        );

        return "redirect:/communities/" + communityId + "/programs";
    }


    @GetMapping("/{id}")
    public String programDetail(@PathVariable long id, Model model) {

        ProgramDetailView view = programApplicationService.getProgramDetail(id);

        model.addAttribute("program", view.program());
        model.addAttribute("tasks", view.tasks());
        model.addAttribute("completion", view.completion());
        model.addAttribute("est", view.estimatedHours());
        model.addAttribute("finished", view.finishedHours());

        model.addAttribute("estMemberHours", view.estMemberHours());
        model.addAttribute("finishedMemberHours", view.finMemberHours());

        return "programs/detail";
    }
}

