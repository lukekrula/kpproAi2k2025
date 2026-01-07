package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.ParticipationRequest;
import cz.uhk.kppro.model.ParticipationRequestStatus;
import cz.uhk.kppro.model.Program;
import cz.uhk.kppro.service.CurrentMemberService;
import cz.uhk.kppro.service.ParticipationRequestService;
import cz.uhk.kppro.service.ProgramService;
import cz.uhk.kppro.service.PartnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/requests")
public class ParticipationRequestController {

    private final ParticipationRequestService participationRequestService;
    private final ProgramService programService;
    private final PartnerService partnerService;
    private CurrentMemberService currentMemberService;

    public ParticipationRequestController(
            ParticipationRequestService participationRequestService,
            ProgramService programService,
            PartnerService partnerService,
            CurrentMemberService currentMemberService
    ) {
        this.participationRequestService = participationRequestService;
        this.programService = programService;
        this.partnerService = partnerService;
        this.currentMemberService = currentMemberService;
    }

   @GetMapping("/create")
    public String createForm(@RequestParam long programId, Model model) {

        Program program = programService.get(programId);

        ParticipationRequest request = new ParticipationRequest();
        request.setProgram(program);

        model.addAttribute("request", request);
        model.addAttribute("partners", partnerService.getAll()); // dropdown
        return "requests/create";
    }
    @GetMapping("/programs/{id}/requests/fragment")
    public String getRequestsFragment(@PathVariable Long id, Model model) {
        Program program = programService.get(id);
        List<ParticipationRequest> requests = participationRequestService.getRequestsForProgram(program.getId());

        model.addAttribute("requests", requests);
        return "fragments/requests-table :: requestsTable";
    }


    @PostMapping("/create")
    public String createRequest(@ModelAttribute ParticipationRequest request) {

        Program program = programService.get(request.getProgram().getId());

        // Always initialize amountApproved to 0 if not provided
        if (request.getAmountApproved() == null) {
            request.setAmountApproved(0.0);
        }

        // Copy program metadata
        request.setManager(program.getManager());

        // Set community (the owner of the program)
        request.setCommunity(program.getCreator());

        // Set creator user (the logged-in member)
        Member creator = currentMemberService.getCurrentMember();
        request.setCreator(creator);

        // Status should always start as PENDING
        request.setStatus(ParticipationRequestStatus.PENDING);

        participationRequestService.save(request);

        return "redirect:/programs/" + program.getId();
    }



}
