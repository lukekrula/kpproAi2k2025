package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Driver;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DriverService {
    Driver get(long id);
    void save(Driver item);
    void delete(long id);
    List<Driver> getAll();
}