package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Organization;
import cz.uhk.kppro.model.Program;
import cz.uhk.kppro.service.MembershipService;
import cz.uhk.kppro.repository.OrganizationRepository;
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
    private final OrganizationRepository organizationRepository;
    private final MembershipService membershipService;

    public ProgramPageController(ProgramRepository programRepository,
                                 OrganizationRepository organizationRepository,
                                 MembershipService membershipService) {
        this.programRepository = programRepository;
        this.organizationRepository = organizationRepository;
        this.membershipService = membershipService;
    }

    @GetMapping
    public String programsPage(@PathVariable long communityId, Model model) {

        Organization community = organizationRepository.findById(communityId)
                .orElseThrow();

        model.addAttribute("community", community);
        return "programs/programs";
    }

    @GetMapping("/fragment")
    public String programsFragment(@PathVariable long communityId, Model model) {

        List<Program> programs = programRepository.findByCreatorId(communityId);

        model.addAttribute("programs", programs);
        return "programs/_program-list :: programList";
    }

    @GetMapping("/new")
    public String newProgramForm(@PathVariable long communityId, Model model) {

        Organization community = organizationRepository.findById(communityId)
                .orElseThrow();

        // NEW: get members via Membership
        List<Member> members = membershipService.getMembersOfOrganization(communityId);

        model.addAttribute("community", community);
        model.addAttribute("members", members);
        model.addAttribute("program", new Program());

        return "programs/new";
    }
}
