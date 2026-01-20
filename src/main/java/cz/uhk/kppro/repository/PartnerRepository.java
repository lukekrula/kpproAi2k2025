package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner,Long> {
    List<Partner> findAllByMembershipsMember(Member member);

}
