package cz.uhk.kppro.dto;

import lombok.Data;

import java.util.List;


@Data
public class TaskDto {
    private long id;
    private String name;
    private boolean completed;

    private long parentId;
    private long programId;
    private MemberDto assignedTo;

    private List<TaskDto> subTasks;
    private TaskDto parent;
    private ProgramDto program;
}