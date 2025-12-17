package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Task;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}

