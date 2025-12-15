package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

}