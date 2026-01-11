package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Item;
import cz.uhk.kppro.model.Organization;
import cz.uhk.kppro.model.OrganizationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

public interface OrganizationService {
    Organization get(long id);
    void save(Organization organization);
    void delete(long id);
    void update(Organization organization);
    List<Organization> getAll();

}
