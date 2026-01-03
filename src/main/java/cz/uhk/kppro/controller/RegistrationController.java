package cz.uhk.kppro.controller;

import cz.uhk.kppro.dto.RegistrationDto;
import cz.uhk.kppro.repository.CommunityRepository;
import cz.uhk.kppro.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;
    private final CommunityRepository communityRepository;

    public RegistrationController(RegistrationService registrationService,
                                  CommunityRepository communityRepository) {
        this.registrationService = registrationService;
        this.communityRepository = communityRepository;
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new RegistrationDto());
        model.addAttribute("communities", communityRepository.findAll());
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

        // If any validation errors exist, re-render the form
        if (bindingResult.hasErrors()) {
            model.addAttribute("communities", communityRepository.findAll());
            return "register";
        }

        registrationService.register(dto);
        return "redirect:/login?registered";
    }


}
