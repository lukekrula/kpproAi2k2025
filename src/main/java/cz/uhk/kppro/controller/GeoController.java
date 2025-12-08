package cz.uhk.kppro.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class GeoController {

    @GetMapping(value = "/api/geo", produces = "application/json")
    public ResponseEntity<String> getGeo() throws IOException {

        ClassPathResource resource = new ClassPathResource("static/data/geojson.json");

        try (InputStream in = resource.getInputStream()) {
            String json = new String(in.readAllBytes());
            return ResponseEntity.ok(json);
        }
    }

    @GetMapping(value = "/api/arch", produces = "application/json")
    public ResponseEntity<String> getArch() throws IOException {

        ClassPathResource resource = new ClassPathResource("static/data/architecture.json");

        try (InputStream in = resource.getInputStream()) {
            String json = new String(in.readAllBytes());
            return ResponseEntity.ok(json);
        }
    }

    @GetMapping(value = "/api/nature", produces = "application/json")
    public ResponseEntity<String> getNature() throws IOException {

        ClassPathResource resource = new ClassPathResource("static/data/nature.json");

        try (InputStream in = resource.getInputStream()) {
            String json = new String(in.readAllBytes());
            return ResponseEntity.ok(json);
        }
    }

    @GetMapping(value = "/api/region", produces = "application/json")
    public ResponseEntity<String> getRegion() throws IOException {

        ClassPathResource resource = new ClassPathResource("static/data/region.json");

        try (InputStream in = resource.getInputStream()) {
            String json = new String(in.readAllBytes());
            return ResponseEntity.ok(json);
        }
    }
}