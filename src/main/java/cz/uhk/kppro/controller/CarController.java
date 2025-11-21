package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Car;
import cz.uhk.kppro.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("list", carService.getAll());
        return "cars_list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("item", new Car());
        return "cars_edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        model.addAttribute("item", carService.get(id));
        return "cars_edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Car item) {
        carService.save(item);
        return "redirect:/cars/";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable int id) {
        Car item = carService.get(id);
        if(item != null) {
            model.addAttribute("item", item);
            return "cars_detail";
        }else{
            return "redirect:/cars/";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Car item = carService.get(id);
        if(item != null) {
            carService.delete(id);
            return "redirect:/cars/";
        }else{
            return "redirect:/cars/";
        }
    }

    @PostMapping("/detail/{id}")
    public String detail(@PathVariable int id,
                         @RequestParam(defaultValue = "false") boolean done) {
        Car item = carService.get(id);
        if(item != null) {
            item.setDone(done);
            carService.save(item);
            return "redirect:/cars/detail/" + String.valueOf(id);
        }else{
            return "redirect:/cars/";
        }
    }

}