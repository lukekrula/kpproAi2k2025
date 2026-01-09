package cz.uhk.kppro.service;

import cz.uhk.kppro.model.*;
import cz.uhk.kppro.repository.MembershipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;

    public MembershipServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public List<Membership> getMembershipsForMember(Member member) {
        return membershipRepository.findByMember(member);
    }

    @Override
    public List<Membership> getMembershipsForOrganization(Organization organization) {
        return membershipRepository.findByOrganization(organization);
    }

    @Override
    public Optional<Membership> getMembership(Member member, Organization organization) {
        return membershipRepository.findByMemberAndOrganization(member, organization);
    }

    @Override
    @Transactional
    public Membership addMembership(Member member, Organization organization, MembershipRole role) {

        // Prevent duplicates
        Optional<Membership> existing =
                membershipRepository.findByMemberAndOrganization(member, organization);

        if (existing.isPresent()) {
            return existing.get();
        }

        Membership membership = new Membership();
        membership.setMember(member);
        membership.setOrganization(organization);
        membership.setRole(role);

        return membershipRepository.save(membership);
    }

    @Override
    @Transactional
    public void removeMembership(Member member, Organization organization) {
        membershipRepository.findByMemberAndOrganization(member, organization)
                .ifPresent(membershipRepository::delete);
    }

    @Override
    public boolean isMemberOf(Member member, Organization organization) {
        return membershipRepository.findByMemberAndOrganization(member, organization).isPresent();
    }

    @Override
    public List<Member> getMembersOfOrganization(Long organizationId){
        return membershipRepository.findByOrganizationId(organizationId)
                .stream()
                .map(Membership::getMember)
                .toList();
    }

}
