package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Partner;
import cz.uhk.kppro.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {

    private PartnerRepository partnerRepository;
    private final CurrentMemberService currentMemberService;

    @Autowired
    public PartnerServiceImpl(
            PartnerRepository partnerRepository,
            CurrentMemberService currentMemberService
    ) {
        this.partnerRepository = partnerRepository;
        this.currentMemberService = currentMemberService;
    }

    @Override
    public Partner getForCurrentUser() {

        // 1) Get the logged-in member
        Member member = currentMemberService.getCurrentMember();

        // 2) Find the partner associated with that member
        return partnerRepository.findByMembersContaining(member)
                .orElseThrow(() -> new IllegalStateException(
                        "Current user is not associated with any partner"
                ));
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



}
