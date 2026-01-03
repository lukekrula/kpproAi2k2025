package cz.uhk.kppro.repository;


import cz.uhk.kppro.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByUserId(Long userId);
}
