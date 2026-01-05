package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Program;
import cz.uhk.kppro.model.Task;

import java.util.List;

public record ProgramDetailView(
        Program program,
        List<Task> tasks,
        double completion,
        int estimatedHours,
        int finishedHours,
        int estMemberHours,
        int totalMemberHours
) {}

