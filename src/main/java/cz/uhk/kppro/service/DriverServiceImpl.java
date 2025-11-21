package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Driver;
import cz.uhk.kppro.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private DriverRepository itemRepository;

    @Autowired
    public void setItemRepository(DriverRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Driver get(long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public void save(Driver item) {
        itemRepository.save(item);
    }

    @Override
    public void delete(long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Driver> getAll() {
        return itemRepository.findAll();
    }
}