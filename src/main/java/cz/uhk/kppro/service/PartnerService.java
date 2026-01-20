package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Partner;

import java.util.List;
import java.util.Optional;

public interface PartnerService {
    Partner get(long id);
    void save(Partner partner);
    void delete(long id);
    void update(Partner partner);
    List<Partner> getAll();
    Partner createPartner(String name, String contactEmail, String contactPerson);
    Optional<Partner> getForCurrentUser();
   List<Partner> getPartnersForMember(Member member);

}
