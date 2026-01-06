package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProgramId(long programId);
    List<Task> findByAssignedToId(long memberId);

    List<Task> findByProgramIdAndAssignedTo(long programId, Member member);
}

