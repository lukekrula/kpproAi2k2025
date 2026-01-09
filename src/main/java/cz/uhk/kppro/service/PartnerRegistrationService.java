package cz.uhk.kppro.service;

import cz.uhk.kppro.dto.PartnerRegistrationDto;
import cz.uhk.kppro.model.Partner;
import cz.uhk.kppro.model.Role;
import cz.uhk.kppro.model.User;
import cz.uhk.kppro.repository.PartnerRepository;
import cz.uhk.kppro.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartnerRegistrationService {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PartnerRepository partnerRepository;

    public PartnerRegistrationService(UserService userService,
                                      RoleRepository roleRepository,
                                      PartnerRepository partnerRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.partnerRepository = partnerRepository;
    }

    @Transactional
    public Partner registerPartner(PartnerRegistrationDto dto) {


        Role partnerRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Default role ROLE_USER not found"));

        if (partnerRole == null) {
            throw new IllegalStateException("Role PARTNER_USER not found");
        }



        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getContactEmail());

        userService.createUser(user, dto.getPassword(), partnerRole.getId());

        Partner partner = new Partner();
        partner.setName(dto.getName());
        partner.setContactEmail(dto.getContactEmail());
        partner.setContactPerson(dto.getContactPerson());
        partner.setUser(user);

        return partnerRepository.save(partner);
    }
}


