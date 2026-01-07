package cz.uhk.kppro.service;

import cz.uhk.kppro.model.ParticipationRequest;


import java.util.List;


public interface ParticipationRequestService {
    ParticipationRequest get(long id);
    void save(ParticipationRequest participationRequest);
    void delete(long id);

    List<ParticipationRequest> getAll();

    List<ParticipationRequest> getRequestsForPartner(long partnerId);
    List<ParticipationRequest> getPendingRequestsForPartner(long partnerId);
    List<ParticipationRequest> getRequestsForCommunity(long communityId);
    List<ParticipationRequest> getRequestsForProgram(long programId);

    void accept(long requestId);
    void deny(long requestId);
    void partial(long requestId, double amountApproved);
}
