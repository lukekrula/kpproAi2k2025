package cz.uhk.kppro.controller;


import cz.uhk.kppro.model.Community;

import cz.uhk.kppro.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/communities")
public class CommunityController {

    private CommunityService communityService;


    @Autowired
    public void setDriverService(CommunityService communityService) {
        this.communityService = communityService;

    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("list", communityService.getAll());
        return "communities";
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("community", new Community());
        return "add";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        Community community = communityService.get(id);
        if (community == null) {
            return "redirect:/communities/";
        }
        model.addAttribute("community", community);
        return "edit";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute Community community) {
        boolean test = community.getId() == 0;
        communityService.save(community);
        return test ? "redirect:/communities/" :
                "redirect:/communities/detail/" + String.valueOf(community.getId());
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable int id) {
        Community community = communityService.get(id);
        if(community != null) {
            model.addAttribute("community", community);
            return "detail";
        }else{
            return "redirect:/communities/";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Community community = communityService.get(id);
        if(community != null) {
            communityService.delete(id);
            return "redirect:/communities/";
        }else{
            return "redirect:/communities/";
        }
    }

    @PostMapping("/detail/{id}")
    public String detail(@PathVariable int id,
                         @RequestParam(defaultValue = "false") boolean done) {
        Community community = communityService.get(id);
        if(community != null) {

            communityService.save(community);
            return "redirect:/communities/detail/" + String.valueOf(id);
        }else{
            return "redirect:/communities/";
        }
    }

}
