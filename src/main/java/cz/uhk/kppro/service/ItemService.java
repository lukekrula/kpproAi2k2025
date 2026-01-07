package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ItemService {
    Item get(long id);
    void save(Item item);
    void delete(long id);
    void update(Item item);
    List<Item> getAll();
}