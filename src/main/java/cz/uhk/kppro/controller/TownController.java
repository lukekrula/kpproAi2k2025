package cz.uhk.kppro.controller;


import cz.uhk.kppro.model.Town;
import cz.uhk.kppro.service.TownService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class TownController {

    private TownService townService;


    @GetMapping("/by-postal-code/{postalCode}")
    public ResponseEntity<String> getTownByPostalCode(@PathVariable String postalCode) {
        try {
            Town town = townService.getByPostalCode(postalCode);
            return ResponseEntity.ok(town.getTown());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
