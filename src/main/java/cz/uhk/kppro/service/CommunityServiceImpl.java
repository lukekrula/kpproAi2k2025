package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Community;
import cz.uhk.kppro.repository.CommunityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository repo;

    public CommunityServiceImpl(CommunityRepository repo) {
        this.repo = repo;
    }

    @Override
    public Community create(Community c) {
        return repo.save(c);
    }

    @Override
    public List<Community> getAll() {
        return repo.findAll();
    }

    @Override
    public Community getById(String id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Community update(String id, Community c) {
        Community existing = getById(id);
        if (existing == null) return null;

        existing.setName(c.getName());
        existing.setIdNumber(c.getIdNumber());
        existing.setFoundingDate(c.getFoundingDate());
        existing.setMembers(c.getMembersList());
        existing.setActivities(c.getActivityList());
        existing.setPartners(c.getPartnerList());

        return repo.save(existing);
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }
}
