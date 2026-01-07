package cz.uhk.kppro.service;

import cz.uhk.kppro.controller.ProgramDetailView;
import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.ParticipationRequest;
import cz.uhk.kppro.model.Program;
import cz.uhk.kppro.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramApplicationService {

    private final ProgramService programService;
    private final ProgramCalculationService programCalculationService;
    private final CurrentMemberService currentMemberService;
    private final TaskCalculationService taskCalc;
    private final TaskQueryService taskQueryService;
    private final ParticipationRequestService participationRequestService;


    public ProgramApplicationService(ProgramService programService,
                                     ProgramCalculationService programCalculationService,
                                     CurrentMemberService currentMemberService,
                                     TaskCalculationService taskCalc,
                                     TaskQueryService taskQueryService,
                                     ParticipationRequestService participationRequestService) {
        this.programService = programService;
        this.programCalculationService = programCalculationService;
        this.currentMemberService = currentMemberService;
        this.taskCalc = taskCalc;
        this.taskQueryService = taskQueryService;
        this.participationRequestService = participationRequestService;
    }

    public List<Program> getPrograms() {
        return programService.getAll();
    }

    public Program getProgram(long id) {
        return programService.get(id);
    }


    public ProgramDetailView getProgramDetail(long programId) {

        Program program = programService.get(programId);
        Member member = currentMemberService.getCurrentMember();
        List<ParticipationRequest> participationRequests = participationRequestService.getRequestsForProgram(programId);

        double completion = programCalculationService.completion(program);
        int estimated = programCalculationService.totalEstimated(program);
        int finished = programCalculationService.totalFinished(program);
        int estMemberHours = taskCalc.totalEstimated(taskQueryService.getTasksForMember(program, member));
        int finMemberHours = taskCalc.totalFinished(taskQueryService.getTasksForMember(program, member));

        List<Task> rootTasks = program.getTasks().stream()
                .filter(t -> t.getParent() == null)
                .toList();

        return new ProgramDetailView(
                program,
                rootTasks,
                completion,
                estimated,
                finished,
                estMemberHours,
                finMemberHours,
                participationRequests
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
