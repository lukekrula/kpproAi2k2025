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
public class ProgramServiceImpl implements ProgramService{
    private final ProgramRepository programRepository;
    private final MemberRepository memberRepository;
    private final CommunityRepository communityRepository;

    public ProgramServiceImpl(ProgramRepository programRepository,
                              MemberRepository memberRepository,
                              CommunityRepository communityRepository) {
        this.programRepository = programRepository;
        this.memberRepository = memberRepository;
        this.communityRepository = communityRepository;
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

}
