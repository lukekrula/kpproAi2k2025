package cz.uhk.kppro.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TaskDto {
    private UUID id;
    private String name;
    private boolean completed;

    private UUID parentId;

    private MemberDto assignedTo;

    private List<TaskDto> subTasks;
}