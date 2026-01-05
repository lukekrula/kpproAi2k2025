package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Community;
import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Program;
import cz.uhk.kppro.model.Task;
import cz.uhk.kppro.repository.CommunityRepository;
import cz.uhk.kppro.repository.MemberRepository;
import cz.uhk.kppro.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService{
    private final ProgramRepository programRepository;
    private final MemberRepository memberRepository;
    private final CommunityRepository communityRepository;
    private final TaskCalculationService taskCalc;

    public ProgramServiceImpl(ProgramRepository programRepository,
                              MemberRepository memberRepository,
                              CommunityRepository communityRepository,
                              TaskCalculationService taskCalc) {
        this.programRepository = programRepository;
        this.memberRepository = memberRepository;
        this.communityRepository = communityRepository;
        this.taskCalc = taskCalc;
    }



    @Override
    @Transactional
    public Program createProgram(long managerId, long communityId, Program program) {

        Member manager = memberRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found: " + managerId));

        Community creator = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found: " + communityId));

        //  Set required fields
        program.setManager(manager);
        program.setCreator(creator);

        //  Optional: initialize empty lists if null
        if (program.getAssignedMembers() == null) {
            program.setAssignedMembers(new ArrayList<>());
        }

        if (program.getSharedWith() == null) {
            program.setSharedWith(new ArrayList<>());
        }

        //  Save and return
        return programRepository.save(program);
    }


    @Override
    public Program get(long id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found: " + id));
    }

    @Override
    public List<Program> getAll() {
        return programRepository.findAll();
    }

    @Override
    public void delete(long id) {
        programRepository.deleteById(id);
    }

    @Override
    public void update(Program program) {
        programRepository.save(program);
    }

    @Override
    @Transactional
    public Program assignMember(long programId, long memberId) {

        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program not found: " + programId));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found: " + memberId));

        //  Avoid duplicates
        if (!program.getAssignedMembers().contains(member)) {
            program.getAssignedMembers().add(member);
        }

        return programRepository.save(program);
    }

    @Override
    public double getProgramCompletion(long programId) {
        Program program = get(programId);

        int totalEstimated = 0;
        int totalFinished = 0;

        for (Task task : program.getTasks()) {
            totalEstimated += taskCalc.totalEstimated(task);
            totalFinished += taskCalc.totalFinished(task);
        }

        if (totalEstimated == 0) return 100;

        double pct = (double) totalFinished / totalEstimated * 100;
        return Math.min(100, Math.max(0, pct));
    }



}
