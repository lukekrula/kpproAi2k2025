package cz.uhk.kppro.service;

import cz.uhk.kppro.controller.ProgramDetailView;
import cz.uhk.kppro.model.Program;
import cz.uhk.kppro.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramApplicationService {

    private final ProgramService programService;
    private final ProgramCalculationService programCalculationService;

    public ProgramApplicationService(ProgramService programService,
                                     ProgramCalculationService programCalculationService) {
        this.programService = programService;
        this.programCalculationService = programCalculationService;
    }

    public List<Program> getPrograms() {
        return programService.getAll();
    }

    public ProgramDetailView getProgramDetail(long programId) {

        Program program = programService.get(programId);

        double completion = programService.getProgramCompletion(programId);
        int estimated = programCalculationService
                .getProgramTotalEstimatedHours(program);
        int finished = programCalculationService
                .getProgramTotalFinishedHours(program);

        List<Task> rootTasks = program.getTasks().stream()
                .filter(t -> t.getParent() == null)
                .toList();

        return new ProgramDetailView(
                program,
                rootTasks,
                completion,
                estimated,
                finished
        );
    }



    public void createProgram(long managerId,
                              long communityId,
                              String name,
                              String description,
                              Integer amountMax) {

        Program program = new Program();
        program.setName(name);
        program.setDescription(description);
        program.setAmountMax(amountMax);

        programService.createProgram(managerId, communityId, program);
    }

}
