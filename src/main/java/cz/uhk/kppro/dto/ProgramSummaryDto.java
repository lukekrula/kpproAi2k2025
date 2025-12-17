package cz.uhk.kppro.dto;
import lombok.Data;

@Data
public class ProgramSummaryDto {
    private Long id;
    private String name;
    private String description;
    private Long managerId;
}
