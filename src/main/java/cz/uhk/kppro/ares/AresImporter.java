package cz.uhk.kppro.ares;

import cz.uhk.kppro.ares.AresRecord;
import cz.uhk.kppro.service.OrganizationService;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Service
public class AresImporter {

    private final AresParser aresParser;
    private final OrganizationService orgService;

    public AresImporter(AresParser aresParser, OrganizationService orgService) {
        this.aresParser = aresParser;
        this.orgService = orgService;
    }

    public void importFromUrl(String url) {
        try (InputStream is = new URL(url).openStream()) {
            List<AresRecord> records = aresParser.parse(is);
            records.forEach(orgService::saveFromAres);
        } catch (Exception e) {
            throw new RuntimeException("ARES import failed", e);
        }
    }


}

