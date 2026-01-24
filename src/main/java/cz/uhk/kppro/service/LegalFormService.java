package cz.uhk.kppro.service;

import cz.uhk.kppro.model.LegalForm;
import cz.uhk.kppro.repository.LegalFormRepository;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;

@Service
public class LegalFormService {

    private final LegalFormRepository repository;

    public LegalFormService(LegalFormRepository repository) {
        this.repository = repository;
    }

    public void importFromUrl(String url) {
        try (InputStream is = new URL(url).openStream()) {
            importFromXml(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to import ROSFORMA from URL: " + url, e);
        }
    }

    public void importFromXml(InputStream xmlStream) {
        try {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(xmlStream);

            NodeList items = doc.getElementsByTagName("POLOZKA");

            for (int i = 0; i < items.getLength(); i++) {
                Element item = (Element) items.item(i);

                String extendedCode = getValue(item, "POLVAZ", "1", "CHODNOTA");
                String extendedName = getValue(item, "POLVAZ", "1", "TEXT");

                String formCode = getValue(item, "POLVAZ", "2", "CHODNOTA");
                String formName = getValue(item, "POLVAZ", "2", "TEXT");

                LegalForm lf = repository.findByExtendedCode(extendedCode)
                        .orElse(new LegalForm());

                lf.setId(extendedCode);
                lf.setExtendedCode(extendedCode);
                lf.setExtendedName(extendedName);
                lf.setFormCode(formCode);
                lf.setFormName(formName);

                // Optional: derive category
                lf.setCategory(deriveCategory(formCode));

                repository.save(lf);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to import ROSFORMA XML", e);
        }
    }

    private String getValue(Element item, String tag, String ref, String childTag) {
        NodeList list = item.getElementsByTagName(tag);
        for (int i = 0; i < list.getLength(); i++) {
            Element el = (Element) list.item(i);
            if (el.getAttribute("ref").equals(ref)) {
                return el.getElementsByTagName(childTag).item(0).getTextContent();
            }
        }
        return null;
    }

    private String deriveCategory(String formCode) {
        if (formCode == null) return "UNKNOWN";

        int code = Integer.parseInt(formCode);

        if (code >= 700 && code < 800) return "SPOLEK";
        if (code >= 100 && code < 200) return "FIRMA";
        if (code >= 117 && code <= 118) return "NADACE";
        if (code == 801 || code == 804 || code == 811) return "OBEC";
        if (code >= 300 && code < 400) return "STATNI_INSTITUCE";

        return "OTHER";
    }
}
