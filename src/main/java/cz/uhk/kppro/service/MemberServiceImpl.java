package cz.uhk.kppro.service.impl;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.User;
import cz.uhk.kppro.repository.MemberRepository;
import cz.uhk.kppro.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public Member createForUser(User user) {

        if (user.getMember() != null) {
            return user.getMember();
        }

        Member member = new Member();
        member.setName(user.getUsername());
        member.setEmail(user.getEmail());
        member.setRole("member");
        member.setUser(user);

        //  important: set reverse side
        user.setMember(member);

        return memberRepository.save(member);
    }


    @Override
    public Member findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found: " + id));
    }


}
