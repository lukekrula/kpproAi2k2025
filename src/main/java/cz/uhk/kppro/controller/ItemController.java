package cz.uhk.kppro.controller;


import cz.uhk.kppro.model.Item;

import cz.uhk.kppro.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;


    @Autowired
    public void setDriverService(ItemService itemService) {
        this.itemService = itemService;

    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("list", itemService.getAll());
        return "items";
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("item", new Item());
        return "add";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        Item item = itemService.get(id);
        if (item == null) {
            return "redirect:/items/";
        }
        model.addAttribute("item", item);
        return "edit";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute Item item) {
        boolean test = item.getId() == 0;
        itemService.save(item);
        return test ? "redirect:/items/" :
                "redirect:/items/detail/" + String.valueOf(item.getId());
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable int id) {
        Item item = itemService.get(id);
        if(item != null) {
            model.addAttribute("item", item);
            return "detail";
        }else{
            return "redirect:/items/";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Item item = itemService.get(id);
        if(item != null) {
            itemService.delete(id);
            return "redirect:/items/";
        }else{
            return "redirect:/items/";
        }
    }

    @PostMapping("/detail/{id}")
    public String detail(@PathVariable int id,
                         @RequestParam(defaultValue = "false") boolean done) {
        Item item = itemService.get(id);
        if(item != null) {
            item.setDone(done);
            itemService.save(item);
            return "redirect:/items/detail/" + String.valueOf(id);
        }else{
            return "redirect:/items/";
        }
    }

}
