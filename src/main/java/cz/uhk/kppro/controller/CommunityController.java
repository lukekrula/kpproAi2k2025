package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Community;
import cz.uhk.kppro.service.AddressService;
import cz.uhk.kppro.service.CommunityService;
import cz.uhk.kppro.service.GeocodingService;
import cz.uhk.kppro.service.TownService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/communities")
public class CommunityController {

    private final CommunityService communityService;
    private final TownService townService;
    private final AddressService addressService;
    private final GeocodingService geocodingService;

    public CommunityController(CommunityService communityService,
                               TownService townService,
                               AddressService addressService,
                               GeocodingService geocodingService) {
        this.communityService = communityService;
        this.townService = townService;
        this.addressService = addressService;
        this.geocodingService = geocodingService;
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
        boolean isNew = community.getId() == 0;
        communityService.save(community);
        return isNew ? "redirect:/communities/" :
                "redirect:/communities/detail/" + community.getId();
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable int id) {
        Community community = communityService.get(id);
        if (community != null) {
            model.addAttribute("community", community);
            return "community-detail";
        } else {
            return "redirect:/communities/";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Community community = communityService.get(id);
        if (community != null) {
            communityService.delete(id);
        }
        return "redirect:/communities/";
    }

    @PostMapping("/detail/{id}")
    public String detail(@PathVariable int id,
                         @RequestParam(defaultValue = "false") boolean done) {
        Community community = communityService.get(id);
        if (community != null) {
            communityService.save(community);
            return "redirect:/communities/detail/" + id;
        } else {
            return "redirect:/communities/";
        }
    }

    // NEW: Geocoding endpoint for your map
    @GetMapping("/geocode")
    @ResponseBody
    public Object geocode(@RequestParam String address) {
        String normalized = addressService.buildGeocodeAddress(address);
        return geocodingService.geocode(normalized);
    }
}
