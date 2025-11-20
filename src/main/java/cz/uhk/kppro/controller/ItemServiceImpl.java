package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Item;
import cz.uhk.kppro.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemServiceImpl implements ItemService {
    @Override
    public Item getItem(long Id) {
        return null;
    }


    @Override
    public void saveItem(Item item) {

    }

    @Override
    public void deleteItem(long Id) {

    }

    @Override
    public void updateItem(Item item) {

    }

    @Override
    public List<Item> getAll() {
        this.Item   
        return List.of();
    }


}
