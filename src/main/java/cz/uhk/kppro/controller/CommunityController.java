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

    @PostMapping
    public Community create(@RequestBody Community c) {
        return service.create(c);
    }

    @GetMapping
    public List<Community> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Community one(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Community update(@PathVariable String id, @RequestBody Community c) {
        return service.update(id, c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
