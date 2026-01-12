package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Partner;
import cz.uhk.kppro.service.PartnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partner")
public class PartnerController {

    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Partner partner = partnerService.getForCurrentUser();
        model.addAttribute("partner", partner);
        return "partner/dashboard";
    }
}
