package cz.uhk.kppro.repository;


import cz.uhk.kppro.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByUserId(Long userId);

    List<Member> findByCommunitiesId(long communityId);
}
