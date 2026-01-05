package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Program;
import cz.uhk.kppro.model.Task;
import org.springframework.stereotype.Service;

@Service
public class ProgramCalculationService {

    private final TaskCalculationService taskCalc;

    public ProgramCalculationService(TaskCalculationService taskCalc) {
        this.taskCalc = taskCalc;
    }

    public int totalEstimated(Program program) {
        return program.getTasks().stream()
                .mapToInt(taskCalc::totalEstimated)
                .sum();
    }

    public int totalFinished(Program program) {
        return program.getTasks().stream()
                .mapToInt(taskCalc::totalFinished)
                .sum();
    }

    public double completion(Program program) {
        int est = totalEstimated(program);
        int fin = totalFinished(program);
        if (est == 0) return 100;
        return Math.min(100, Math.max(0, (double) fin / est * 100));
    }

    public int memberEstimated(Program program, Member member) {
        return program.getTasks().stream()
                .mapToInt(t -> taskCalc.totalEstimatedForMember(t, member))
                .sum();
    }

    public int memberFinished(Program program, Member member) {
        return program.getTasks().stream()
                .mapToInt(t -> taskCalc.totalFinishedForMember(t, member))
                .sum();
    }

    public double memberCompletion(Program program, Member member) {
        int est = memberEstimated(program, member);
        int fin = memberFinished(program, member);
        if (est == 0) return 100;
        return Math.min(100, Math.max(0, (double) fin / est * 100));
    }


    public int getProgramTotalEstimatedHours(Program program) {

        int total = 0;
        for (Task task : program.getTasks()) {
            total += taskCalc.totalEstimated(task); }
        return total;
    }


    public int getProgramTotalFinishedHours(Program program) {

        int total = 0;
        for (Task task : program.getTasks()) {
            total += taskCalc.totalFinished(task); }
        return total;
    }
}

