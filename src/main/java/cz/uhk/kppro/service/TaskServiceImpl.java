package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Program;
import cz.uhk.kppro.model.Task;
import cz.uhk.kppro.repository.MemberRepository;
import cz.uhk.kppro.repository.ProgramRepository;
import cz.uhk.kppro.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProgramRepository programRepository;
    private final MemberRepository memberRepository;

    public TaskServiceImpl(TaskRepository taskRepository,
                           ProgramRepository programRepository,
                           MemberRepository memberRepository) {
        this.taskRepository = taskRepository;
        this.programRepository = programRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public Task createTask(long programId, long assignedMemberId, Task task) {

        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program not found: " + programId));

        Member assignedTo = memberRepository.findById(assignedMemberId)
                .orElseThrow(() -> new RuntimeException("Member not found: " + assignedMemberId));

        task.setProgram(program);
        task.setAssignedTo(assignedTo);
        task.setParent(null);

        return taskRepository.save(task);
    }

    @Override
    public Task addSubtask(long parentTaskId, Task subtask) {

        Task parent = taskRepository.findById(parentTaskId)
                .orElseThrow(() -> new RuntimeException("Parent task not found: " + parentTaskId));

        subtask.setParent(parent);
        subtask.setProgram(parent.getProgram());
        subtask.setAssignedTo(parent.getAssignedTo());

        return taskRepository.save(subtask);
    }

    @Override
    public Task get(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public void delete(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void update(Task task) {
        taskRepository.save(task);
    }
}
