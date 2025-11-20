package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ItemService {
    Item getItem(long Id);
    void saveItem(Item item);
    void deleteItem(long Id);
    void updateItem(Item item);
    List<Item> getAll();


}
