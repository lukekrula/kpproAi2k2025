package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Item;
import cz.uhk.kppro.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class MainController {



    private ItemService itemService;
    @Autowired
    public MainController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/")
    public String index(Model model) {
        String name = "Moje jmeno";
        return "index";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "response body test - ne4um";
    }

    @GetMapping("/detail/{index}")
    public String detail(Model model, @PathVariable int id){
        Item item = itemService.getItem(id);
        if(item!=null){
            model.addAttribute("item",item);
            return "detail";
        }
        else {
            return "redirect";
        }

    }
}
