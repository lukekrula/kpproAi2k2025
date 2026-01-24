package cz.uhk.kppro.ares;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AresCronJob {

    private final AresImporter importer;

    public AresCronJob(AresImporter importer) {
        this.importer = importer;
    }

    @Scheduled(cron = "${ares.import.cron}")
    public void run() {
        importer.importFromUrl("https://ares.gov.cz/.../ares.xml");
        System.out.println("ARES import completed.");
    }
}

