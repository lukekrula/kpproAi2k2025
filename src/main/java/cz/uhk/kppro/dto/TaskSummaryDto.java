package cz.uhk.kppro.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class TaskSummaryDto {
    private UUID id;
    private String name;
    private boolean completed;
    private UUID parentId;
}
