package cz.uhk.kppro.service;

import cz.uhk.kppro.dto.OrganizationDetailDto;
import cz.uhk.kppro.ares.AresRecord;
import cz.uhk.kppro.model.LegalForm;
import cz.uhk.kppro.model.Organization;
import cz.uhk.kppro.repository.LegalFormRepository;
import cz.uhk.kppro.repository.OrganizationRepository;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final LegalFormRepository legalFormRepository;
    public OrganizationServiceImpl(OrganizationRepository organizationRepository,
                                   LegalFormRepository legalFormRepository) {
        this.organizationRepository = organizationRepository;
        this.legalFormRepository = legalFormRepository;
    }

    @Override
    public Organization get(String id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Organization not found: " + id));
    }

    @Override
    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public void delete(String id) {
        organizationRepository.deleteById(id);
    }

    @Override
    public List<Organization> getAll() {
        return organizationRepository.findAll();
    }


    public OrganizationDetailDto getOrganizationDetail(String id) {
        Organization org = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        LegalForm lf = legalFormRepository
                .findByExtendedCode(org.getExtendedLegalForm())
                .orElse(null);

        OrganizationDetailDto dto = new OrganizationDetailDto();
        dto.setId(org.getId());
        dto.setIco(org.getIco());
        dto.setName(org.getName());

        dto.setLegalFormCode(org.getLegalForm());
        dto.setExtendedLegalFormCode(org.getExtendedLegalForm());

        if (lf != null) {
            dto.setLegalFormName(lf.getFormName());
            dto.setExtendedLegalFormName(lf.getExtendedName());
            dto.setCategory(lf.getCategory());
        }

        return dto;
    }

    private GeoJsonPoint geocode(String address) {
        // TODO: implementace
        return null;
    }

    public void saveFromAres(AresRecord rec) {

        if (rec == null || rec.ico == null) {
            return; // nebo logovat
        }

        Organization org = organizationRepository.findByIco(rec.ico)
                .orElse(new Organization());

        // --- Identifikace ---
        org.setIco(rec.ico);
        org.setName(rec.name);

        // --- Právní forma ---
        org.setLegalForm(rec.legalForm);               // FORMA
        org.setExtendedLegalForm(rec.extendedLegalForm); // ROSFORMA

        // --- Adresa ---
        org.setStreet(rec.street);
        org.setHouseNumber(rec.houseNumber);
        org.setOrientationNumber(rec.orientationNumber);
        org.setCity(rec.city);
        org.setPostalCode(rec.postalCode);
        org.setFullAddress(rec.fullAddress);

        // --- Územní jednotky ---
        org.setOkresLau(rec.okresLau);

        // --- Datum vzniku / zániku ---
        org.setDateEstablished(rec.dateEstablished);
        org.setDateTerminated(rec.dateTerminated);

        // --- Geolokace (jen pokud není) ---
        if (org.getLocation() == null && rec.fullAddress != null) {
            GeoJsonPoint point = geocode(rec.fullAddress);
            if (point != null) {
                org.setLocation(point);
            }
        }

        organizationRepository.save(org);
    }



}



