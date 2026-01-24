package cz.uhk.kppro.service;

import cz.uhk.kppro.ares.AresRecord;
import cz.uhk.kppro.model.Organization;
import java.util.List;

public interface OrganizationService {

    Organization get(String id);

    Organization save(Organization organization);

    void delete(String id);

    List<Organization> getAll();

    void saveFromAres(AresRecord aresRecord);
}
