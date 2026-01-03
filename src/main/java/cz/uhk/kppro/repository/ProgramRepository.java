package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program,Long> {
    List<Program> findByCreatorId(long creatorId);

}
