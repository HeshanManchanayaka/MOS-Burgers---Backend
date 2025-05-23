package edu.icet.repository;

import edu.icet.entity.ItemEntity;
import edu.icet.util.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, String> {
    ItemEntity searchByCode(String code);

    List<ItemEntity> searchByName(String name);

    List<ItemEntity> searchByCategory(ItemCategory category);
}
