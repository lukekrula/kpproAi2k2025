package cz.uhk.kppro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    @ResponseBody
    public String forbidden() {
        return "<h1>Forbidden - you shall not pass</h1>";
    }

    @GetMapping("/logged")
    public String logged() {
        return "logged";
    }

    @GetMapping("/admin/administration")
    public String adminPage() {
        return "admin/administration";
    }

    @GetMapping("/login-error")
    public String loginError() {
        return "login-error"; // resolves to login-error.html
    }
}