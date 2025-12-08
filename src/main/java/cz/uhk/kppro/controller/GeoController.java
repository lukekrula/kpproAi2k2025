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
}