package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Community;
import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Membership;
import cz.uhk.kppro.model.MembershipRole;
import cz.uhk.kppro.repository.CommunityRepository;
import cz.uhk.kppro.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public CommunityServiceImpl(CommunityRepository communityRepository,
                                MemberRepository memberRepository) {
        this.communityRepository = communityRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public Community get(long id) {
        return communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found: " + id));
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

    @Override
    @Transactional
    public Community assignMember(long communityId, long memberId) {
        Membership membership = new Membership();
        Community community = get(communityId);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found: " + memberId));
        membership.setRole(MembershipRole.COMMUNITY_MEMBER);
        membership.setMember(member);



        return communityRepository.save(community);
    }
}
