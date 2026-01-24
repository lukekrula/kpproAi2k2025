package cz.uhk.kppro.controller;

import cz.uhk.kppro.ares.AresImporter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/import")
public class ImportController {

    private final AresImporter aresImporter;

    public ImportController(AresImporter aresImporter) {
        this.aresImporter = aresImporter;
    }

    @PostMapping("/ares")
    public String importAres() {
        aresImporter.importFromUrl("TVŮJ_URL");
        return "ARES import started";
    }
}
