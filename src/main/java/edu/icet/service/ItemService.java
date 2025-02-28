package edu.icet.service;


import edu.icet.dto.Item;
import edu.icet.entity.ItemEntity;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    void addItem(Item item);

    List<Item> getAll();

    void update(Item item);

    Item searchByCode(String code);

    List<Item> searchByName(String name);
}
