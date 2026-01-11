package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Organization;

import cz.uhk.kppro.repository.OrganizationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Organization get(long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Organization not found: " + id));
    }

    @Override
    public void save(Organization organization) {
        organizationRepository.save(organization);
    }

    @Override
    public void delete(long id) {
        organizationRepository.deleteById(id);
    }

    @Override
    public void update(Organization organization) {
        organizationRepository.save(organization);
    }

    @Override
    public List<Organization> getAll() {
        return organizationRepository.findAll();
    }
}
