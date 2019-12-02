package com.sea_emperor.repository;

import com.sea_emperor.factory.ItemData;
import com.sea_emperor.model.ItemCategory;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<ItemData, Integer>
{
    boolean existsByItemNameAndItemCategory( String itemName, ItemCategory itemCategory );
}
