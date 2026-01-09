package cz.uhk.kppro.service;

import cz.uhk.kppro.model.*;

import java.util.List;
import java.util.Optional;

public interface MembershipService {

    List<Membership> getMembershipsForMember(Member member);

    List<Membership> getMembershipsForOrganization(Organization organization);

    Optional<Membership> getMembership(Member member, Organization organization);

    Membership addMembership(Member member, Organization organization, MembershipRole role);

    void removeMembership(Member member, Organization organization);

    boolean isMemberOf(Member member, Organization organization);

    List<Member> getMembersOfOrganization(Long organizationId);

}
