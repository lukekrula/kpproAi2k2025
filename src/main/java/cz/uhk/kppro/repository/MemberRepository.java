package cz.uhk.kppro.repository;


import cz.uhk.kppro.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {


    List<Member> findByCommunitiesId(long communityId);
    Optional<Member> findByUserId(long userId);

}
