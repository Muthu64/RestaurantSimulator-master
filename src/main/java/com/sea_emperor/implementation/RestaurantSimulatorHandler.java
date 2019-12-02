package com.sea_emperor.implementation;

import com.sea_emperor.DBOperations.DBOperations;
import com.sea_emperor.factory.ItemData;
import com.sea_emperor.factory.ItemFactory;
import com.sea_emperor.model.Item;
import com.sea_emperor.model.ItemCategory;
import com.sea_emperor.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantSimulatorHandler
{
    @Autowired
    private ItemRepository itemRepository;

    public void createInitialItemsDump()
    {
        List<ItemData> itemDataList = ItemFactory.buildAndGetItems();
        this.itemRepository.saveAll( itemDataList );
    }

    public ItemData addItem( ItemData itemData )
    {
        return this.itemRepository.save( itemData );
    }

    public boolean validateItemExists( String itemName, ItemCategory itemCategory )
    {
        return this.itemRepository.existsByItemNameAndItemCategory( itemName, itemCategory );
    }

    public boolean validateAllItemsAreAvailable( List<Item> items )
    {
        return items.stream().allMatch( item -> this.validateItemExists( item.getItemName(), item.getItemCategory() ) );
    }

    public List<ItemData> findAllItems()
    {
        List<ItemData> itemDataList = new ArrayList<>();
        this.itemRepository.findAll().forEach( itemDataList::add );
        return itemDataList;
    }

    public boolean validateItemIdAvailable( int itemId )
    {
        return this.itemRepository.existsById( itemId );
    }


    public DBOperations deleteItem( int itemId )
    {
        DBOperations dbOperations = null;

        this.itemRepository.deleteById( itemId );
        dbOperations = DBOperations.ITEM_DELETED_SUCCESSFULLY;
        return dbOperations;
    }
}
