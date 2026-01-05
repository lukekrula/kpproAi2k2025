package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Community;
import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Program;
import cz.uhk.kppro.repository.CommunityRepository;
import cz.uhk.kppro.repository.MemberRepository;
import cz.uhk.kppro.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final MemberRepository memberRepository;
    private final CommunityRepository communityRepository;

    private final ProgramCalculationService programCalculationService;

    public ProgramServiceImpl(ProgramRepository programRepository,
                              MemberRepository memberRepository,
                              CommunityRepository communityRepository,
                              ProgramCalculationService programCalculationService) {

        this.programRepository = programRepository;
        this.memberRepository = memberRepository;
        this.communityRepository = communityRepository;
        this.programCalculationService = programCalculationService;
    }


    @Override
    @Transactional
    public Program createProgram(long managerId, long communityId, Program program) {

        Member manager = memberRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found: " + managerId));

        Community creator = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found: " + communityId));

        program.setManager(manager);
        program.setCreator(creator);

        if (program.getAssignedMembers() == null) {
            program.setAssignedMembers(new ArrayList<>());
        }

        if (program.getSharedWith() == null) {
            program.setSharedWith(new ArrayList<>());
        }

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

        if (!program.getAssignedMembers().contains(member)) {
            program.getAssignedMembers().add(member);
        }

        return programRepository.save(program);
    }


    @Override
    public double getProgramCompletion(long programId) {
        Program program = get(programId);
        return programCalculationService.completion(program);
    }

    @Override
    public int getProgramTotalEstimatedHours(long programId) {
        Program program = get(programId);
        return programCalculationService.totalEstimated(program);
    }

    @Override
    public int getProgramTotalFinishedHours(long programId) {
        Program program = get(programId);
        return programCalculationService.totalFinished(program);
    }

    @Override
    public int memberEstimated(Program program, Member member) {
        return programCalculationService.memberEstimated(program, member);
    }

    @Override
    public int memberFinished(Program program, Member member) {
        return programCalculationService.memberFinished(program, member);
    }

    @Override
    public double memberCompletion(Program program, Member member) {
        return programCalculationService.memberCompletion(program, member);
    }
}
