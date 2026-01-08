package cz.uhk.kppro.controller;


import cz.uhk.kppro.model.MembershipRole;
import cz.uhk.kppro.model.OrganizationType;
import cz.uhk.kppro.service.MemberService;
import cz.uhk.kppro.service.MembershipService;
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
    private final MembershipService membershipService;


    public CommunityMembershipController(MemberRepository memberRepository,
                                         CommunityRepository communityRepository,
                                         MemberService memberService,
                                         MembershipService membershipService) {
        this.memberRepository = memberRepository;
        this.communityRepository = communityRepository;
        this.memberService = memberService;
        this.membershipService = membershipService;
    }

    @GetMapping("/my")
    public String myCommunities(Model model,
                                @AuthenticationPrincipal MyUserDetails userDetails) {

        long userId = userDetails.getUser().getId();

        Member member = memberRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Member created = memberService.createForUser(userDetails.getUser());
                    return memberRepository.save(created);
                });

        List<Community> communities = member.getMemberships().stream()
                .filter(m -> m.getOrganization().getType() == OrganizationType.COMMUNITY)
                .map(m -> (Community) m.getOrganization())
                .toList();

        model.addAttribute("member", member);
        model.addAttribute("communities", communities);

        return "communities/my-communities";
    }




    @GetMapping("/join")
    public String joinCommunityPage(Model model,
                                    @AuthenticationPrincipal MyUserDetails userDetails) {

        long userId = userDetails.getUser().getId();

        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Member not found for userId: " + userId));

        List<Community> joined = member.getMemberships().stream()
                .filter(m -> m.getOrganization().getType() == OrganizationType.COMMUNITY)
                .map(m -> (Community) m.getOrganization())
                .toList();

        List<Community> all = communityRepository.findAll();
        List<Community> available = all.stream()
                .filter(c -> !joined.contains(c))
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

        // NEW: Add membership instead of adding to member.getCommunities()
        membershipService.addMembership(member, community, MembershipRole.COMMUNITY_MEMBER);

        return "redirect:/communities/my";
    }


}

