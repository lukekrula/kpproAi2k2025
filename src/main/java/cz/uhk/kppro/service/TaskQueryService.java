package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Program;
import cz.uhk.kppro.model.Task;
import cz.uhk.kppro.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskQueryService {

    private final TaskRepository taskRepository;

    public TaskQueryService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksForMemberInProgram(long programId, Member member) {
        return taskRepository.findByProgramIdAndAssignedTo(programId, member);
    }


    public List<Task> getTasksForMember(Program program, Member member) {
        return program.getTasks().stream()
                .filter(t -> t.getAssignedTo().equals(member))
                .toList();
    }
}



