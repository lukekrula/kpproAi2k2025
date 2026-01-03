package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Community;
import cz.uhk.kppro.model.Program;
import cz.uhk.kppro.repository.CommunityRepository;
import cz.uhk.kppro.repository.ProgramRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/communities/{communityId}/programs")
public class ProgramPageController {

    private final ProgramRepository programRepository;
    private final CommunityRepository communityRepository;

    public ProgramPageController(ProgramRepository programRepository,
                                 CommunityRepository communityRepository) {
        this.programRepository = programRepository;
        this.communityRepository = communityRepository;
    }

    @GetMapping
    public String programsPage(@PathVariable long communityId, Model model) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow();
        model.addAttribute("community", community);
        return "programs/programs"; // main page
    }

    @GetMapping("/fragment")
    public String programsFragment(@PathVariable long communityId, Model model) {
        List<Program> programs = programRepository.findByCreatorId(communityId);
        model.addAttribute("programs", programs);
        return "programs/_program-list :: programList";
    }

    @GetMapping("/new")
    public String newProgramForm(@PathVariable long communityId, Model model) {
        model.addAttribute("communityId", communityId);
        model.addAttribute("program", new Program());
        return "programs/new";
    }
}
