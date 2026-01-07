package cz.uhk.kppro.service;

import cz.uhk.kppro.model.ParticipationRequest;
import cz.uhk.kppro.model.ParticipationRequestStatus;
import cz.uhk.kppro.repository.ParticipationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationRequestServiceImpl implements ParticipationRequestService {

    private final ParticipationRequestRepository participationRequestRepository;

    public ParticipationRequestServiceImpl(ParticipationRequestRepository participationRequestRepository) {
        this.participationRequestRepository = participationRequestRepository;
    }

    @Override
    public ParticipationRequest get(long id) {
        return participationRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ParticipationRequest not found"));
    }

    @Override
    public void save(ParticipationRequest participationRequest) {
        participationRequestRepository.save(participationRequest);
    }

    @Override
    public void delete(long id) {
        participationRequestRepository.deleteById(id);
    }

    @Override
    public List<ParticipationRequest> getAll() {
        return participationRequestRepository.findAll();
    }


    @Override
    public List<ParticipationRequest> getRequestsForPartner(long partnerId) {
        return participationRequestRepository.findByPartnerId(partnerId);
    }

    @Override
    public List<ParticipationRequest> getPendingRequestsForPartner(long partnerId) {
        return participationRequestRepository.findByPartnerIdAndStatus(
                partnerId,
                ParticipationRequestStatus.PENDING
        );
    }

    @Override
    public List<ParticipationRequest> getRequestsForCommunity(long communityId) {
        return participationRequestRepository.findByCommunityId(communityId);
    }

    @Override
    public List<ParticipationRequest> getRequestsForProgram(long programId) {
        return participationRequestRepository.findByProgramId(programId);
    }


    @Override
    public void accept(long requestId) {
        ParticipationRequest req = get(requestId);
        req.setStatus(ParticipationRequestStatus.ACCEPTED);
        participationRequestRepository.save(req);
    }

    @Override
    public void deny(long requestId) {
        ParticipationRequest req = get(requestId);
        req.setStatus(ParticipationRequestStatus.DENIED);
        participationRequestRepository.save(req);
    }

    @Override
    public void partial(long requestId, double amountApproved) {
        ParticipationRequest req = get(requestId);
        req.setStatus(ParticipationRequestStatus.PARTIAL);
        req.setAmountRequested(amountApproved); // or create a new field "amountApproved"
        participationRequestRepository.save(req);
    }
}
