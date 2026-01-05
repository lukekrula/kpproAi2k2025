package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.security.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentMemberService {

    private final MemberService memberService;

    public CurrentMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public Member getCurrentMember() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        long userId = userDetails.getUser().getId();
        return memberService.findByUserId(userId);
    }
}
