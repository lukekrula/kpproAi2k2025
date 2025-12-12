package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Community;
import cz.uhk.kppro.service.CommunityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communities")
public class CommunityController {

    private final CommunityService service;

    public CommunityController(CommunityService service) {
        this.service = service;
    }



    @GetMapping
    public List<Community> all() {
        return service.getAll();
    }



}
