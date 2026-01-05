package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Program;

import java.util.List;

public interface ProgramService {
    Program createProgram(long managerId, long communityId, Program program);

    Program get(long id);
    List<Program> getAll();
    void delete(long id);
    void update(Program program);
    Program assignMember(long programId, long memberId);
    double getProgramCompletion(long programId);

    int getProgramTotalEstimatedHours(long programId);

    int getProgramTotalFinishedHours(long programId);

    int memberEstimated(Program program, Member member);

    int memberFinished(Program program, Member member);

    double memberCompletion(Program program, Member member);
}

