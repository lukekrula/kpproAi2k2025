package cz.uhk.kppro.cron;

import cz.uhk.kppro.service.LegalFormService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LegalFormCronJob {

    private final LegalFormService service;

    public LegalFormCronJob(LegalFormService service) {
        this.service = service;
    }

    @Scheduled(cron = "${legalform.import.cron}")
    public void runImport() {
        String url = "https://apl.czso.cz/iSMS/ciselniky?format=xml&cis=ROSFORMA";
        service.importFromUrl(url);
        System.out.println("ROSFORMA import completed.");
    }
}
