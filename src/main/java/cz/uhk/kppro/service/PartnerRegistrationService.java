package cz.uhk.kppro.service;

import cz.uhk.kppro.dto.PartnerRegistrationDto;
import cz.uhk.kppro.model.*;
import cz.uhk.kppro.repository.PartnerRepository;
import cz.uhk.kppro.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartnerRegistrationService {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PartnerRepository partnerRepository;
    private final MemberService memberService;
    private final MembershipService membershipService;
    private final OrganizationService organizationService;

    public PartnerRegistrationService(UserService userService,
                                      RoleRepository roleRepository,
                                      PartnerRepository partnerRepository,
                                      MemberService memberService,
                                      MembershipService membershipService,
                                      OrganizationService organizationService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.partnerRepository = partnerRepository;
        this.memberService = memberService;
        this.membershipService = membershipService;
        this.organizationService = organizationService;
    }

    @Transactional
    public Partner registerPartner(PartnerRegistrationDto dto) {

        // 1) Load role for the user account
        Role partnerRole = roleRepository.findByName("PARTNER_USER")
                .orElseThrow(() -> new IllegalStateException("Role PARTNER_USER not found"));

        // 2) Create User
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getContactEmail());

        userService.createUser(user, dto.getPassword(), partnerRole.getId());

        // 3) Create Member linked to the User
        Member member = memberService.createForUser(user);

        // 4) Create Partner (Organization)


        Partner partner = new Partner();
        partner.setName(dto.getName());
        partner.setContactEmail(dto.getContactEmail());
        partner.setContactPerson(dto.getContactPerson());

        partner = partnerRepository.save(partner);
        System.out.println("Created partner with ID: " + partner.getId());

        MembershipRole role =
                partner.getType() == OrganizationType.PARTNER
                        ? MembershipRole.PARTNER_MEMBER
                        : MembershipRole.COMMUNITY_MEMBER;

        // 5) Create membership
        membershipService.addMembership(member, partner, role);


        return partner;
    }

}


