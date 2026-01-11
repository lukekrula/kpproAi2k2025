package cz.uhk.kppro.controller;

import cz.uhk.kppro.dto.RegistrationDto;
import cz.uhk.kppro.model.OrganizationType;
import cz.uhk.kppro.repository.OrganizationRepository;
import cz.uhk.kppro.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;
    private final OrganizationRepository organizationRepository;

    public RegistrationController(RegistrationService registrationService,
                                  OrganizationRepository organizationRepository) {
        this.registrationService = registrationService;
        this.organizationRepository = organizationRepository;
    }


    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new RegistrationDto());
        model.addAttribute("organizations", organizationRepository.findByType(OrganizationType.COMMUNITY));

        return "register";
    }

    @PostMapping("/register")
    public String processForm(
            @Valid @ModelAttribute("user") RegistrationDto dto,
            BindingResult bindingResult,
            Model model) {

        // Custom password match validation
        if (!dto.getPassword().equals(dto.getRepeatPassword())) {
            bindingResult.rejectValue("repeatPassword", "error.repeatPassword", "Passwords do not match");
        }
        dto.setOrganizationType("COMMUNITY");
        // If any validation errors exist, re-render the form
        bindingResult.getAllErrors().forEach(err -> {
            System.out.println("VALIDATION ERROR: " + err.toString());
        });

        if (bindingResult.hasErrors()) {
            model.addAttribute("organizations", organizationRepository.findAll());

            return "register";
        }

        registrationService.register(dto);
        return "redirect:/login?registered";
    }


}
