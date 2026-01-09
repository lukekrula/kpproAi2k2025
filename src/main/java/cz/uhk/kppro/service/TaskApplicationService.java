package cz.uhk.kppro.service;

import cz.uhk.kppro.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskApplicationService {

    private final TaskService taskService;
    private final ProgramService programService;
    private final MembershipService membershipService;

    public TaskApplicationService(TaskService taskService,
                                  ProgramService programService,
                                  MembershipService membershipService) {
        this.taskService = taskService;
        this.programService = programService;
        this.membershipService = membershipService;
    }


    public List<Task> getRootTasks(long programId) {
        return taskService.getByProgram(programId).stream()
                .filter(t -> t.getParent() == null)
                .toList();
    }

    public Task getTask(long taskId) {
        return taskService.get(taskId);
    }

    public List<Task> getSubtasks(long taskId) {
        return taskService.get(taskId).getSubTasks();
    }

    public List<Member> getAssignableMembers(long programId) {

        Program program = programService.get(programId);

        // The creator is now an Organization (Community or Partner)
        Organization creatorOrg = program.getCreator();

        // Get all memberships for that organization
        List<Membership> memberships =
                membershipService.getMembershipsForOrganization(creatorOrg);

        // Map memberships → members
        return memberships.stream()
                .map(Membership::getMember)
                .toList();
    }




    public void createTaskWithSubtasks(long programId,
                                       long memberId,
                                       String name,
                                       List<String> subTasks) {

        Task parent = new Task(name);
        Task savedParent = taskService.createTask(programId, memberId, parent);

        if (subTasks == null) return;

        for (String subName : subTasks) {
            if (subName != null && !subName.trim().isEmpty()) {
                taskService.addSubtask(savedParent.getId(), new Task(subName));
            }
        }
    }

    public void addSubtask(long parentId, String name) {
        taskService.addSubtask(parentId, new Task(name));
    }

    public void completeTask(long taskId) {
        Task task = taskService.get(taskId);
        task.complete();
        taskService.update(task);
    }
}


