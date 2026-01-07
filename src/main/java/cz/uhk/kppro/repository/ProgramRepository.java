package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Program;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program,Long> {

    @Query("""
    SELECT p FROM Program p
    LEFT JOIN FETCH p.assignedMembers
    LEFT JOIN FETCH p.sharedWith
    LEFT JOIN FETCH p.creator
    WHERE p.id = :id
""")
    Optional<Program> findDetail(long id);
    List<Program> findByCreatorId(long creatorId);
}
