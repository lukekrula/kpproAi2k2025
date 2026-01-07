package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.ParticipationRequest;
import cz.uhk.kppro.model.ParticipationRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRequestRepository extends JpaRepository<ParticipationRequest,Long> {

    List<ParticipationRequest> findByPartnerId(Long partnerId);

    List<ParticipationRequest> findByPartnerIdAndStatus(Long partnerId, ParticipationRequestStatus status);

    List<ParticipationRequest> findByCommunityId(Long communityId);

    List<ParticipationRequest> findByProgramId(Long programId);
}
