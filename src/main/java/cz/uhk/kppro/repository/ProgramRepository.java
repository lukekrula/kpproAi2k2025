package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program,Long> {
}
