package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends MongoRepository<Organization, String> {

    List<Organization> findByLegalForm(String legalForm);

    List<Organization> findByNameContainingIgnoreCase(String name);

    List<Organization> findByNace(String nace);

    Optional<Organization> findByIco(String ico);
}
