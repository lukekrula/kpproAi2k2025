package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Driver;
import cz.uhk.kppro.service.CarService;
import cz.uhk.kppro.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    private DriverService driverService;
    private CarService carService;

    @Autowired
    public void setDriverService(DriverService driverService,  CarService carService) {
        this.driverService = driverService;
        this.carService = carService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("list", driverService.getAll());
        return "drivers_list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("cars", carService.getAll());
        model.addAttribute("item", new Driver());
        return "drivers_edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        model.addAttribute("cars", carService.getAll());
        model.addAttribute("item", driverService.get(id));
        return "drivers_edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Driver item) {
        boolean test = item.getId() == 0;
        driverService.save(item);
        return test ? "redirect:/drivers/" :
                "redirect:/drivers/detail/" + String.valueOf(item.getId());
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable int id) {
        Driver item = driverService.get(id);
        if(item != null) {
            model.addAttribute("item", item);
            return "drivers_detail";
        }else{
            return "redirect:/drivers/";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Driver item = driverService.get(id);
        if(item != null) {
            driverService.delete(id);
            return "redirect:/drivers/";
        }else{
            return "redirect:/drivers/";
        }
    }

    @PostMapping("/detail/{id}")
    public String detail(@PathVariable int id,
                         @RequestParam(defaultValue = "false") boolean done) {
        Driver item = driverService.get(id);
        if(item != null) {
            item.setDrunk(done);
            driverService.save(item);
            return "redirect:/drivers/detail/" + String.valueOf(id);
        }else{
            return "redirect:/drivers/";
        }
    }

}