package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Community;
import cz.uhk.kppro.model.Item;

import java.util.List;

public interface CommunityService {
    Community get(long id);
    void save(Community community);
    void delete(long id);
    void update(Community community);
    List<Community> getAll();


}
