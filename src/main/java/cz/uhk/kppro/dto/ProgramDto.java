package cz.uhk.kppro.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProgramDto {
    private Long id;
    private String name;
    private String description;
    private Long creatorCommunityId;
    private List<Long> sharedWithCommunityIds;
    private List<MemberDto> assignedMembers;
    private MemberDto manager;
    private List<TaskDto> tasks;
}
