package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Membership;
import cz.uhk.kppro.model.OrganizationType;
import cz.uhk.kppro.repository.OrganizationRepository;
import cz.uhk.kppro.service.OrganizationService;
import cz.uhk.kppro.service.MembershipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partner")
public class PartnerController {

    private final OrganizationRepository organizationRepository;

    public PartnerController( OrganizationRepository organizationRepository) {

        this.organizationRepository = organizationRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("partner", organizationRepository.findByType(OrganizationType.PARTNER));
        
        return "partner/dashboard";
    }
}
