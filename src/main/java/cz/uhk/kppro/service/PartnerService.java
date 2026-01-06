package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Partner;

import java.util.List;

public interface PartnerService {
    Partner get(long id);
    void save(Partner partner);
    void delete(long id);
    void update(Partner partner);
    List<Partner> getAll();
}
