package cz.uhk.kppro.controller;

import cz.uhk.kppro.dto.PartnerRegistrationDto;
import cz.uhk.kppro.service.PartnerRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partner")
public class PartnerRegistrationController {

    private final PartnerRegistrationService registrationService;

    public PartnerRegistrationController(PartnerRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("partner", new PartnerRegistrationDto());
        return "partner/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("partner") PartnerRegistrationDto dto) {
        registrationService.registerPartner(dto);
        return "redirect:/login?registered";
    }
}

