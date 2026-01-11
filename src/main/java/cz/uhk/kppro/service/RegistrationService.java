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
    private final OrganizationService organizationService;
    private final PartnerService partnerService;
    private final MembershipService membershipService;
    private final RoleService roleService;

    public RegistrationService(UserService userService,
                               MemberService memberService,
                               OrganizationService organizationService,
                               PartnerService partnerService,
                               MembershipService membershipService,
                               RoleService roleService) {
        this.userService = userService;
        this.memberService = memberService;
        this.organizationService = organizationService;
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

        // 3) Load selected organization
        Organization organization = organizationService.get(dto.getOrganizationId());

        // 4) Determine membership role based on organization type
        MembershipRole role =
                organization.getType() == OrganizationType.PARTNER
                        ? MembershipRole.PARTNER_ADMIN
                        : MembershipRole.COMMUNITY_MEMBER;

        // 5) Create membership
        membershipService.addMembership(member, organization, role);
    }


}
