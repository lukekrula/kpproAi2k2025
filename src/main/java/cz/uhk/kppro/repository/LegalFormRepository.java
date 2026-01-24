package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.LegalForm;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LegalFormRepository extends MongoRepository<LegalForm, String> {

    Optional<LegalForm> findByExtendedCode(String extendedCode);

    Optional<LegalForm> findByFormCode(String formCode);

    Optional<LegalForm> findByExtendedName(String extendedName);

    Optional<LegalForm> findByFormName(String formName);
}
