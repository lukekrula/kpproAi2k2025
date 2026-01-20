package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Membership;
import cz.uhk.kppro.model.OrganizationType;
import cz.uhk.kppro.model.Partner;
import cz.uhk.kppro.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartnerServiceImpl implements PartnerService {

    private PartnerRepository partnerRepository;
    private final CurrentMemberService currentMemberService;
    private final MembershipService membershipService;

    @Autowired
    public PartnerServiceImpl(
            PartnerRepository partnerRepository,
            CurrentMemberService currentMemberService,
            MembershipService membershipService
    ) {
        this.partnerRepository = partnerRepository;
        this.currentMemberService = currentMemberService;
        this.membershipService = membershipService;
    }

    @Override
    public Optional<Partner> getForCurrentUser() {

        // 1) Get the logged-in member
        Member member = currentMemberService.getCurrentMember();

        // 2) Find the partner associated with that member
        return membershipService.findByMemberAndOrganizationType(member, OrganizationType.PARTNER);

    }

    @Override
    public Partner get(long id) {
        return partnerRepository.findById(id).get();
    }

    @Override
    public void save(Partner partner) {
        partnerRepository.save(partner);
    }

    @Override
    public void delete(long id) {
        partnerRepository.deleteById(id);
    }

    @Override
    public void update(Partner partner) {
        partnerRepository.save(partner);
    }

    @Override
    public List<Partner> getAll() {
        return partnerRepository.findAll();
    }

    @Override
    public Partner createPartner(String name, String contactEmail, String contactPerson) {
        Partner partner = new Partner();
        partner.setName(name);
        partner.setContactEmail(contactEmail);
        partner.setContactPerson(contactPerson);
        // if using OrganizationType:
        // partner.setType(OrganizationType.PARTNER);
        return partnerRepository.save(partner);
    }

    @Override
    public List<Partner> getPartnersForMember(Member member) {
        return partnerRepository.findAllByMembershipsMember(member);
    }




}
