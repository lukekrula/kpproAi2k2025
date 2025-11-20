package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Item;
import cz.uhk.kppro.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item get(long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void delete(long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void update(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }
}