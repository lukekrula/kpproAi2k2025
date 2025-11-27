package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Community;
import java.util.List;

public interface CommunityService {

    Community create(Community c);

    List<Community> getAll();

    Community getById(String id);

    Community update(String id, Community c);

    void delete(String id);
}
