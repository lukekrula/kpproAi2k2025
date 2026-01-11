package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Organization;
import cz.uhk.kppro.model.OrganizationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    List<Organization> findByType(OrganizationType type);

}

