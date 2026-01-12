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

    public PartnerRegistrationService(UserService userService,
                                      RoleRepository roleRepository,
                                      PartnerRepository partnerRepository,
                                      MemberService memberService,
                                      MembershipService membershipService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.partnerRepository = partnerRepository;
        this.memberService = memberService;
        this.membershipService = membershipService;
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
        Member member = new Member();
        member.setUser(user);
        member.setName(dto.getContactPerson());
        member.setEmail(dto.getContactEmail());
        memberService.createForUser(user);

        // 4) Create Partner (Organization)
        Partner partner = new Partner();
        partner.setName(dto.getName());
        partner.setContactEmail(dto.getContactEmail());
        partner.setContactPerson(dto.getContactPerson());

        partner = partnerRepository.save(partner);

        // 5) Create Membership linking Member ↔ Partner
        membershipService.addMembership(
                member,
                partner,
                MembershipRole.PARTNER_ADMIN   // or PARTNER_USER depending on your design
        );

        return partner;
    }

}


