package edu.icet.controller;

import edu.icet.dto.Item;
import edu.icet.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@CrossOrigin
public class ItemController {

    final ItemService itemService;

    @PostMapping("/add")
    public void add(@RequestBody Item item){
        itemService.addItem(item);
    }

    @GetMapping("/getAll")
    public List<Item> getAll(){
        return itemService.getAll();
    }

    @PutMapping("/update")
    public void updateItem(@RequestBody Item item){
        itemService.update(item);
    }

    @GetMapping("/search-by-code/{code}")
    public Item searchByCode(@PathVariable String code){
        return itemService.searchByCode(code);
    }

    @GetMapping("/search-by-name/{name}")
    public List<Item> searchByName(@PathVariable String name){
        return itemService.searchByName(name);
    }

}
