package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Community;
import java.util.List;

public interface CommunityService {

    Community create(Community community);

    Community getById(Long id);

    List<Community> getAll();

    Community update(Long id, Community community);

    void delete(Long id);
}
