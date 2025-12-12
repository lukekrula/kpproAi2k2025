package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Community;
import cz.uhk.kppro.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;

    @Autowired
    public CommunityServiceImpl(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Override
    public Community get(long id) {
        return communityRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Community community) {
        communityRepository.save(community);
    }

    @Override
    public void delete(long id) {
        communityRepository.deleteById(id);
    }

    @Override
    public void update(Community community) {
        communityRepository.save(community);
    }

    @Override
    public List<Community> getAll() {
        return communityRepository.findAll();
    }
}
