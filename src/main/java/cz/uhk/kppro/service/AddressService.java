package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Town;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AddressService {

    private final TownService townService;

    public AddressService(TownService townService) {
        this.townService = townService;
    }

    // Extract 5-digit postal code (with or without space)
    public String extractPostalCode(String address) {
        Matcher m = Pattern.compile("(\\d{3})\\s?(\\d{2})").matcher(address);
        if (m.find()) {
            return m.group(1) + m.group(2); // "54372"
        }
        return null;
    }

    // Extract first number in the string = house number
    public String extractHouseNumber(String address) {
        Matcher m = Pattern.compile("(\\d+)").matcher(address);
        return m.find() ? m.group(1) : "";
    }

    // Resolve canonical town name from DB
    public String resolveTownName(String postalCode) {
        try {
            Town town = townService.getByPostalCode(postalCode);
            return town.getTown();
        } catch (RuntimeException e) {
            return null;
        }
    }

    // Build the final Czech-friendly geocode address
    public String buildGeocodeAddress(String originalAddress) {

        String postal = extractPostalCode(originalAddress);
        String house = extractHouseNumber(originalAddress);
        String town = postal != null ? resolveTownName(postal) : null;

        if (postal != null && town != null) {
            // Format: "543 72 Rudník 205 Czech Republic"
            String formattedPostal = postal.substring(0, 3) + " " + postal.substring(3);
            return formattedPostal + " " + town + " " + house + " Czech Republic";
        }

        // Fallback
        return originalAddress + " Czech Republic";
    }
}
