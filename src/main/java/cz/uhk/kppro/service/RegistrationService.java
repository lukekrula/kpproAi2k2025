package cz.uhk.kppro.service;

import cz.uhk.kppro.dto.RegistrationDto;
import cz.uhk.kppro.model.*;
import cz.uhk.kppro.repository.CommunityRepository;
import cz.uhk.kppro.repository.MemberRepository;
import cz.uhk.kppro.repository.RoleRepository;
import cz.uhk.kppro.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final UserService userService;
    private final MemberService memberService;
    private final CommunityService communityService;
    private final MembershipService membershipService;

    public RegistrationService(UserService userService,
                               MemberService memberService,
                               CommunityService communityService,
                               MembershipService membershipService) {
        this.userService = userService;
        this.memberService = memberService;
        this.communityService = communityService;
        this.membershipService = membershipService;
    }

    @Transactional
    public void register(RegistrationDto dto) {

        if (!dto.getPassword().equals(dto.getRepeatPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // 1) Create User
        User user = userService.createUser(dto.getUsername(), dto.getEmail(), dto.getPassword());

        // 2) Resolve or create Community
        Community community = resolveCommunity(dto);

        // 3) Create Member for this User
        Member member = memberService.createForUser(user);

        // 4) Add Membership (COMMUNITY_MEMBER)
        membershipService.addMembership(member, community, MembershipRole.COMMUNITY_MEMBER);
    }

    private Community resolveCommunity(RegistrationDto dto) {
        if (dto.getNewCommunityName() != null && !dto.getNewCommunityName().isBlank()) {
            return communityService.create(dto.getNewCommunityName());
        }
        return communityService.getById(dto.getCommunityId());
    }
}
