package cz.uhk.kppro.mapper;

import cz.uhk.kppro.dto.TaskDto;
import cz.uhk.kppro.dto.TaskSummaryDto;
import cz.uhk.kppro.model.Task;

import java.util.stream.Collectors;


public class TaskMapper {

    public static TaskDto toDto(Task task) {
        if (task == null) return null;

        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setCompleted(task.isCompleted());

        if (task.getParent() != null) {
            dto.setParentId(task.getParent().getId());
        }

        if (task.getProgram() != null) {
            dto.setProgramId(task.getProgram().getId());
        }

        dto.setAssignedTo(MemberMapper.toDto(task.getAssignedTo()));

        dto.setSubTasks(
                task.getSubTasks()
                        .stream()
                        .map(TaskMapper::toDto)
                        .collect(Collectors.toList())
        );

        return dto;
    }

    public static TaskSummaryDto toSummary(Task task) {
        if (task == null) return null;

        TaskSummaryDto dto = new TaskSummaryDto();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setCompleted(task.isCompleted());

        if (task.getParent() != null) {
            dto.setParentId(task.getParent().getId());
        }

        if (task.getProgram() != null) {
            dto.setProgramId(task.getProgram().getId());
        }

        if (task.getAssignedTo() != null) {
            dto.setAssignedMemberId(task.getAssignedTo().getId());
        }

        return dto;
    }
}
