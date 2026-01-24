package cz.uhk.kppro.ares;

import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class AresParser {

    public List<AresRecord> parse(InputStream xmlStream) {
        List<AresRecord> records = new ArrayList<>();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                AresRecord currentRecord = null;
                StringBuilder content = new StringBuilder();

                String currentRef = null; // "1" = ROSFORMA, "2" = FORMA
                String currentElement = null;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    content.setLength(0);

                    if (qName.equals("POLOZKA")) {
                        currentRecord = new AresRecord();
                    }

                    if (qName.equals("POLVAZ")) {
                        currentRef = attributes.getValue("ref");
                    }

                    if (qName.equals("CHODNOTA") || qName.equals("TEXT")) {
                        currentElement = qName;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) {
                    content.append(ch, start, length);
                }

                @Override
                public void endElement(String uri, String localName, String qName) {

                    if (currentRecord != null && currentRef != null && currentElement != null) {
                        String value = content.toString().trim();

                        if (currentRef.equals("1")) { // ROSFORMA
                            if (currentElement.equals("CHODNOTA")) {
                                currentRecord.extendedFormCode = value;
                            } else if (currentElement.equals("TEXT")) {
                                currentRecord.extendedFormName = value;
                            }
                        }

                        if (currentRef.equals("2")) { // FORMA
                            if (currentElement.equals("CHODNOTA")) {
                                currentRecord.formCode = value;
                            } else if (currentElement.equals("TEXT")) {
                                currentRecord.formName = value;
                            }
                        }
                    }

                    if (qName.equals("POLVAZ")) {
                        currentRef = null;
                    }

                    if (qName.equals("POLOZKA")) {
                        records.add(currentRecord);
                        currentRecord = null;
                    }

                    currentElement = null;
                }
            };

            parser.parse(xmlStream, handler);

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse ARES XML", e);
        }

        return records;
    }
}
