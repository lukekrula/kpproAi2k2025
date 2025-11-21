package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Car;
import cz.uhk.kppro.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository itemRepository;

    @Autowired
    public void setItemRepository(CarRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Car get(long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public void save(Car item) {
        itemRepository.save(item);
    }

    @Override
    public void delete(long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Car> getAll() {
        return itemRepository.findAll();
    }
}