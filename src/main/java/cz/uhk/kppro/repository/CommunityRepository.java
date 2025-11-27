package cz.uhk.kppro.repository;
import cz.uhk.kppro.model.Community;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CommunityRepository extends MongoRepository<Community, String> {
    Optional<Community> findByIdNumber(String idNumber);
}
