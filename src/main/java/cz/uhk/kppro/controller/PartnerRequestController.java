package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.ParticipationRequest;
import cz.uhk.kppro.service.ParticipationRequestService;
import cz.uhk.kppro.service.PartnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/partners")
public class PartnerRequestController {

    private final ParticipationRequestService participationRequestService;
    private final PartnerService partnerService;

    public PartnerRequestController(
            ParticipationRequestService participationRequestService,
            PartnerService partnerService
    ) {
        this.participationRequestService = participationRequestService;
        this.partnerService = partnerService;
    }

    @GetMapping("/{id}/requests")
    public String listRequestsForPartner(@PathVariable long id, Model model) {

        var partner = partnerService.get(id);
        List<ParticipationRequest> requests = participationRequestService.getRequestsForPartner(id);

        model.addAttribute("partner", partner);
        model.addAttribute("requests", requests);

        return "partner/requests";
    }
}

