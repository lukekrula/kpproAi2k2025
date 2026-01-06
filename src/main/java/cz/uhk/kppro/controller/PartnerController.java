package cz.uhk.kppro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partner")
public class PartnerController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "partner/dashboard";
    }
}
