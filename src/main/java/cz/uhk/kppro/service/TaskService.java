package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    Task createTask(long programId, long assignedMemberId, Task task);
    Task addSubtask(long parentTaskId, Task subtask);
    Task get(long id);
    List<Task> getAll();
    void delete(long id);
    void update(Task task);
    List<Task> getByProgram(long programId);
    List<Task> getByMember(long memberId);

}
