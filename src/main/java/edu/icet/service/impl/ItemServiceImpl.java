package edu.icet.service.impl;

import edu.icet.dto.Item;
import edu.icet.entity.ItemEntity;
import edu.icet.repository.ItemRepository;
import edu.icet.service.ItemService;
import edu.icet.util.ItemCategory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    final ItemRepository repository;
    final ModelMapper mapper;
    @Override
    public void addItem(Item item) {
        repository.save(mapper.map(item, ItemEntity.class));
    }

    @Override
    public List<Item> getAll() {
        ArrayList<Item> itemList = new ArrayList<>();

        List<ItemEntity> list= repository.findAll();

        list.forEach(ItemEntity->{
            itemList.add(mapper.map(ItemEntity,Item.class));
        });
        return itemList;
    }

    @Override
    public void update(Item item) {
        repository.save(mapper.map(item, ItemEntity.class));
    }

    @Override
    public Item searchByCode(String code) {
        return mapper.map( repository.searchByCode(code), Item.class);
    }

    @Override
    public List<Item> searchByName(String name) {
        ArrayList<Item> itemList = new ArrayList<>();

        List<ItemEntity> list= repository.searchByName(name);

        list.forEach(ItemEntity->{
            itemList.add(mapper.map(ItemEntity,Item.class));
        });
        return itemList;
    }

    @Override
    public List<Item> searchByCategory(ItemCategory category) {
        ArrayList<Item> itemList = new ArrayList<>();

        List<ItemEntity> list= repository.searchByCategory(category);

        list.forEach(ItemEntity->{
            itemList.add(mapper.map(ItemEntity,Item.class));
        });
        return itemList;
    }
}
