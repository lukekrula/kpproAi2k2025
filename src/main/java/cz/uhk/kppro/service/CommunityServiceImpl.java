package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Community;
import cz.uhk.kppro.repository.CommunityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;

    public CommunityServiceImpl(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Override
    public Community create(Community community) {
        return communityRepository.save(community);
    }

    @Override
    public Community getById(Long id) {
        return communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found"));
    }

    @Override
    public List<Community> getAll() {
        return communityRepository.findAll();
    }

    @Override
    public Community update(Long id, Community community) {
        Community db = getById(id);
        db.setName(community.getName());
        db.setIdNumber(community.getIdNumber());
        db.setFoundingDate(community.getFoundingDate());
        db.setMembersList(community.getMembersList());
        db.setActivityList(community.getActivityList());
        db.setPartnerList(community.getPartnerList());
        return communityRepository.save(db);
    }

    @Override
    public void delete(Long id) {
        communityRepository.deleteById(id);
    }
}
