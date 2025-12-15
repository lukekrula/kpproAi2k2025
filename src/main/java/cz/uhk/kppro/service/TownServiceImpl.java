package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Town;
import cz.uhk.kppro.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownServiceImpl implements TownService {

    private TownRepository townRepository;

    @Autowired
    public void setTownRepository(TownRepository townRepository) {
        this.townRepository = townRepository;
    }


    @Override
    public Town get(long id) {
        return townRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Town not found: " + id));
    }

    @Override
    public Town getByPostalCode(String postalCode) {
        return townRepository.findByPostalCode(postalCode)
                .orElseThrow(() -> new RuntimeException("Town not found: " + postalCode));
    }

    @Override
    public void save(Town town) {
        townRepository.save(town);
    }

    @Override
    public void delete(long id) {
        townRepository.deleteById(id);
    }

    @Override
    public void update(Town town) {
        townRepository.save(town);
    }



    @Override
    public List<Town> getAll() {
        return townRepository.findAll();
    }
}

