package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Membership;
import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Organization;
import cz.uhk.kppro.model.OrganizationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    // All memberships for a given user
    List<Membership> findByMember(Member member);

    // All memberships for a given organization (community or partner)
    List<Membership> findByOrganization(Organization organization);

    // Optional: find a specific membership
    Optional<Membership> findByMemberAndOrganization(Member member, Organization organization);
    Optional<Membership> findByMemberAndOrganizationType(Member member, OrganizationType type);

    // Optional: find by member + organization type
    List<Membership> findByMemberId(long memberId);
    List<Membership> findByOrganizationId(Long organizationId);

}
