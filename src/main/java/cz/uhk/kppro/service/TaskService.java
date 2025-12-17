package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    Task createTask(String name);

    void addSubTask(UUID parentId, String subTaskName);

    void completeTask(UUID id);

    List<Task> findAllRootTasks();
}