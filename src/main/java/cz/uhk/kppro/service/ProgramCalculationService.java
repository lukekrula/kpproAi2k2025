package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Program;
import org.springframework.stereotype.Service;

@Service
public class ProgramCalculationService {

    private final TaskCalculationService taskCalc;


    public ProgramCalculationService(TaskCalculationService taskCalc) {
        this.taskCalc = taskCalc;
    }

    public int totalEstimated(Program program) {
        return taskCalc.totalEstimated(program.getTasks());
    }

    public int totalFinished(Program program) {
        return taskCalc.totalFinished(program.getTasks());
    }

    public double completion(Program program) {
        int est = totalEstimated(program);
        int fin = totalFinished(program);
        if (est == 0) return 100;
        return (double) fin / est * 100;
    }
}
