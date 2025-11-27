package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Community;
import cz.uhk.kppro.service.CommunityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communities")
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @PostMapping
    public Community create(@RequestBody Community community) {
        return communityService.create(community);
    }

    @GetMapping("/{id}")
    public Community getById(@PathVariable Long id) {
        return communityService.getById(id);
    }

    @GetMapping
    public List<Community> getAll() {
        return communityService.getAll();
    }

    @PutMapping("/{id}")
    public Community update(@PathVariable Long id, @RequestBody Community community) {
        return communityService.update(id, community);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        communityService.delete(id);
    }
}
