package cz.uhk.kppro.controller;


import cz.uhk.kppro.service.MemberService;
import org.springframework.ui.Model;

import cz.uhk.kppro.model.Community;
import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.repository.CommunityRepository;
import cz.uhk.kppro.repository.MemberRepository;
import cz.uhk.kppro.security.MyUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/communities")
public class CommunityMembershipController {

    private final MemberRepository memberRepository;
    private final CommunityRepository communityRepository;
    private final MemberService memberService;


    public CommunityMembershipController(MemberRepository memberRepository,
                                         CommunityRepository communityRepository, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.communityRepository = communityRepository;
        this.memberService = memberService;
    }

    @GetMapping("/my")
    public String myCommunities(Model model,
                                @AuthenticationPrincipal MyUserDetails userDetails) {

        long userId = userDetails.getUser().getId();

        Member member = memberRepository.findByUserId(userId)
                .orElseGet(() -> {
                    // SAFETY NET: auto-create Member if missing
                    Member created = memberService.createForUser(userDetails.getUser());
                    return memberRepository.save(created);
                });

        model.addAttribute("member", member);
        model.addAttribute("communities", member.getCommunities());

        return "communities/my-communities";
    }



    @GetMapping("/join")
    public String joinCommunityPage(Model model,
                                    @AuthenticationPrincipal MyUserDetails userDetails) {

        long userId = userDetails.getUser().getId();

        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Member not found for userId: " + userId));

        List<Community> all = communityRepository.findAll();
        List<Community> available = all.stream()
                .filter(c -> !member.getCommunities().contains(c))
                .toList();

        model.addAttribute("available", available);

        return "communities/join-community";
    }


    @PostMapping("/join/{id}")
    public String joinCommunity(@PathVariable Long id,
                                @AuthenticationPrincipal MyUserDetails userDetails) {

        long userId = userDetails.getUser().getId();

        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Member not found for userId: " + userId));

        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        member.getCommunities().add(community);
        memberRepository.save(member);

        return "redirect:/communities/my";
    }

}

