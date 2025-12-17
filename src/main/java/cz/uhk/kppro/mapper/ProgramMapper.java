package cz.uhk.kppro.mapper;

import cz.uhk.kppro.dto.ProgramDto;
import cz.uhk.kppro.dto.ProgramSummaryDto;
import cz.uhk.kppro.model.Program;

import java.util.stream.Collectors;

public class ProgramMapper {

    public static ProgramDto toDto(Program program) {
        if (program == null) return null;

        ProgramDto dto = new ProgramDto();
        dto.setId(program.getId());
        dto.setName(program.getName());
        dto.setDescription(program.getDescription());

        if (program.getCreator() != null) {
            dto.setCreatorCommunityId(program.getCreator().getId());
        }

        dto.setSharedWithCommunityIds(
                program.getSharedWith()
                        .stream()
                        .map(c -> c.getId())
                        .collect(Collectors.toList())
        );

        dto.setAssignedMembers(
                program.getAssignedMembers()
                        .stream()
                        .map(MemberMapper::toDto)
                        .collect(Collectors.toList())
        );

        dto.setManager(MemberMapper.toDto(program.getManager()));

        dto.setTasks(
                program.getTasks()
                        .stream()
                        .map(TaskMapper::toDto)
                        .collect(Collectors.toList())
        );

        return dto;
    }

    public static ProgramSummaryDto toSummary(Program program) {
        if (program == null) return null;

        ProgramSummaryDto dto = new ProgramSummaryDto();
        dto.setId(program.getId());
        dto.setName(program.getName());
        dto.setDescription(program.getDescription());

        if (program.getManager() != null) {
            dto.setManagerId(program.getManager().getId());
        }

        return dto;
    }
}
