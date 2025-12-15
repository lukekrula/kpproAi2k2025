package cz.uhk.kppro.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class GeocodingService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Object geocode(String normalizedAddress) {

        String url = "https://nominatim.openstreetmap.org/search" +
                "?format=json" +
                "&addressdetails=1" +
                "&countrycodes=cz" +
                "&q=" + URLEncoder.encode(normalizedAddress, StandardCharsets.UTF_8);

        return restTemplate.getForObject(url, Object.class);
    }
}
