package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Town;

import java.util.List;

public interface TownService {

    Town get(long id);
    void save(Town town);
    void delete(long id);
    void update(Town town);
    List<Town> getAll();
}
