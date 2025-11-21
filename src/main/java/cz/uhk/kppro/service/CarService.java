package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    Car get(long id);
    void save(Car item);
    void delete(long id);
    List<Car> getAll();
}