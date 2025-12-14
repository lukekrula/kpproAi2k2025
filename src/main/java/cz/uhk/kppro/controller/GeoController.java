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

        // 1. Split on comma FIRST
        String[] parts = address.split(",");

        if (parts.length < 2) {
            return address + " Czech Republic";
        }

        String housePart = parts[0].trim();   // "č.p. 205"
        String restPart  = parts[1].trim();   // "543 72 Rudník"

        // 2. Clean house number part
        housePart = housePart
                .replace("č.p.", "")
                .replace("č.ev.", "")
                .replace("č.p", "")
                .replace("č.ev", "")
                .replace("č.", "")
                .replace("čp.", "")
                .replace("čp", "")
                .trim();

        // Extract only digits → building number
        String buildingNumber = housePart.replaceAll("[^0-9]", "");

        // 3. Extract village name (ignore postal code)
        String[] tokens = restPart.split("\\s+");

        // Village is everything after the postal code (first 2 tokens)
        StringBuilder villageBuilder = new StringBuilder();
        for (int i = 2; i < tokens.length; i++) {
            villageBuilder.append(tokens[i]).append(" ");
        }
        String village = villageBuilder.toString().trim();

        // 4. Build final Czech-friendly format
        return buildingNumber + " " + village + " Czech Republic";
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
        System.out.println("LOOKUP ADDRESS = " + address);

        return java.util.Collections.emptyList();
    }

}