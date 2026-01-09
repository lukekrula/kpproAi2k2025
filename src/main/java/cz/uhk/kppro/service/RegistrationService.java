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
    private final PartnerService partnerService;
    private final MembershipService membershipService;
    private final RoleService roleService;

    public RegistrationService(UserService userService,
                               MemberService memberService,
                               CommunityService communityService,
                               PartnerService partnerService,
                               MembershipService membershipService,
                               RoleService roleService) {
        this.userService = userService;
        this.memberService = memberService;
        this.communityService = communityService;
        this.partnerService = partnerService;
        this.membershipService = membershipService;
        this.roleService = roleService;
    }

    @Transactional
    public void register(RegistrationDto dto) {

        // 1) Create User
        User user = userService.createUser(
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword()
        );

        // 2) Create Member
        Member member = memberService.createForUser(user);

        // 3) Determine organization type
        Organization organization;

        switch (dto.getOrganizationType()) {

            case "PARTNER" -> {
                organization = partnerService.createPartner(
                        dto.getPartnerName(),
                        dto.getPartnerContactEmail(),
                        dto.getPartnerContactPerson()
                );

                membershipService.addMembership(
                        member,
                        organization,
                        MembershipRole.PARTNER_ADMIN
                );
            }

            case "COMMUNITY" -> {
                organization = communityService.resolveCommunity(dto);

                membershipService.addMembership(
                        member,
                        organization,
                        MembershipRole.COMMUNITY_MEMBER
                );
            }

            default -> throw new IllegalArgumentException(
                    "Unknown organization type: " + dto.getOrganizationType()
            );
        }
    }

}
