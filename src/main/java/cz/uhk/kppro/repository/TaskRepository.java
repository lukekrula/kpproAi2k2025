package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

