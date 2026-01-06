package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Partner;
import cz.uhk.kppro.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}
