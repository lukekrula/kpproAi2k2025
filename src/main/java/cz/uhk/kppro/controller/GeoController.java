package cz.uhk.kppro.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class GeoController {

    private String normalizeAddress(String address) {
        // Remove Czech house number prefixes
        String cleaned = address
                .replace("č.p.", "")
                .replace("č.ev.", "")
                .replace("č.p", "")
                .replace("č.ev", "")
                .replace("č.", "")
                .replace("čp.", "")
                .replace("čp", "")
                .replace(",", "")
                .trim();

        // Split into parts
        // Example: "205 543 72 Rudník"
        String[] parts = cleaned.split("\\s+");

        // Extract house number (first number)
        String house = parts[0];

        // Extract postal code (54372 or 543 72)
        String postal = parts[1] + parts[2];

        // Extract village (remaining)
        String village = parts[3];

        // Return normalized Czech-friendly format
        return village + " " + house + " Czech Republic";
    }



    @GetMapping(value = "/api/geo", produces = "application/json")
    public ResponseEntity<String> getGeo() throws IOException {

        ClassPathResource resource = new ClassPathResource("static/data/geojson.json");

        try (InputStream in = resource.getInputStream()) {
            String json = new String(in.readAllBytes());
            return ResponseEntity.ok(json);
        }
    }

    @GetMapping(value = "/api/rud", produces = "application/json")
    public ResponseEntity<String> getRud() throws IOException {

        ClassPathResource resource = new ClassPathResource("static/data/rud.json");

        try (InputStream in = resource.getInputStream()) {
            String json = new String(in.readAllBytes());
            return ResponseEntity.ok(json);
        }
    }

    @GetMapping(value = "/api/trut", produces = "application/json")
    public ResponseEntity<String> getTrut() throws IOException {

        ClassPathResource resource = new ClassPathResource("static/data/trutnovsko.json");

        try (InputStream in = resource.getInputStream()) {
            String json = new String(in.readAllBytes());
            return ResponseEntity.ok(json);
        }
    }

    @GetMapping(value = "/api/naturear", produces = "application/json")
    public ResponseEntity<String> getNatureArea() throws IOException {

        ClassPathResource resource = new ClassPathResource("static/data/natureLandmark.json");

        try (InputStream in = resource.getInputStream()) {
            String json = new String(in.readAllBytes());
            return ResponseEntity.ok(json);
        }
    }

    @GetMapping(value = "/api/pyr", produces = "application/json")
    public ResponseEntity<String> getPyr() throws IOException {

        ClassPathResource resource = new ClassPathResource("static/data/czech.json");

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

    @GetMapping("/api/geocode")
    public Object geocode(@RequestParam String address) {
        RestTemplate rest = new RestTemplate();

        String[] attempts = {
                address,
                address.replaceAll(".*,", ""), // remove first part
                address.replaceAll(".*,", "").trim(), // city only
                address.replaceAll("[^0-9 ]", "") // postal code only
        };

        for (String attempt : attempts) {
            String url = "https://nominatim.openstreetmap.org/search?format=json&q=" + attempt;
            Object result = rest.getForObject(url, Object.class);

            if (result instanceof java.util.List<?> list && !list.isEmpty()) {
                return result;
            }
        }

        return java.util.Collections.emptyList();
    }

}