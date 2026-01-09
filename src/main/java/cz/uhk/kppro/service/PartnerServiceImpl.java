package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Partner;
import cz.uhk.kppro.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {

    private PartnerRepository partnerRepository;

    @Autowired
    public void setPartnerRepository(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
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
