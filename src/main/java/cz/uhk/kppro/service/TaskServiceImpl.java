package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Task;
import cz.uhk.kppro.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task createTask(String name) {
        return repository.save(new Task(name));
    }

    @Override
    public void addSubTask(UUID parentId, String subTaskName) {
        Task parent = repository.findById(parentId)
                .orElseThrow(() -> new IllegalArgumentException("Parent not found"));

        Task child = new Task(subTaskName);
        parent.addSubTask(child);

        repository.save(parent); // cascades
    }

    @Override
    public void completeTask(UUID id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        task.complete();
    }

    @Override
    public List<Task> findAllRootTasks() {
        return repository.findAll()
                .stream()
                .filter(task -> task.getParent() == null)
                .toList();
    }
}
