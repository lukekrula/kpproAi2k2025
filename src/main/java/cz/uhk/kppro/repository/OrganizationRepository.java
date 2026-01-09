package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {}

