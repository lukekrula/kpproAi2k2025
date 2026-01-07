package cz.uhk.kppro.repository;
import cz.uhk.kppro.model.Community;
import cz.uhk.kppro.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {

    Optional<Community> findByRegistrationNumber(String registrationNumber);

}
