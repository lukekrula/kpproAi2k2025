package cz.uhk.kppro.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

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
        return "login";
    }



    @GetMapping("/items/map")
    public String map() {
        return "/map";
    }

    @GetMapping("/items/arch")
    public String arch() {
        return "/architecture";
    }





    @GetMapping("/403")
    @ResponseBody
    public String forbidden() {
        return "<h1>Forbidden - you shall not pass</h1>";
    }

    @GetMapping("/500")
    @ResponseBody
    public String internalServerError() {
        return "<h1>Internal server error - are you sure you mapped it right?</h1>";
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
        return "login-error";
    }


}