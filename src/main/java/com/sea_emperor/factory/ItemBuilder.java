package com.sea_emperor.factory;

import com.sea_emperor.configurations.CommonConfigurations;
import com.sea_emperor.model.ItemCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemBuilder
{
    private List<ItemData> itemDataList;

    ItemBuilder()
    {
        itemDataList = new ArrayList<>();
    }

    public ItemBuilder createNonVegItems()
    {
        ItemData chickenBriyani = new ItemData( 1, CommonConfigurations.CHICKEN_BRIYANI, 10, ItemCategory.NON_VEG );
        ItemData fishFry = new ItemData( 2, CommonConfigurations.FISH_FRY, 15, ItemCategory.NON_VEG );
        ItemData tandoori = new ItemData( 3, CommonConfigurations.TANDOORI, 20, ItemCategory.NON_VEG );

        itemDataList.add( chickenBriyani );
        itemDataList.add( fishFry );
        itemDataList.add( tandoori );

        return this;
    }

    public ItemBuilder createVegItems()
    {

        ItemData veg_briyani = new ItemData( 4, CommonConfigurations.VEG_BRIYANI, 10, ItemCategory.VEG );
        ItemData chappathi = new ItemData( 5, CommonConfigurations.CHAPPATHI, 5, ItemCategory.VEG );
        itemDataList.add( veg_briyani );
        itemDataList.add( chappathi );
        return this;
    }

    public ItemBuilder createSnacksItems()
    {
        ItemData samosa = new ItemData( 6, CommonConfigurations.SAMOSA, 10, ItemCategory.SNACKS );
        ItemData bajji = new ItemData( 7, CommonConfigurations.BAJJI, 5, ItemCategory.SNACKS );
        ItemData sandwich = new ItemData( 8, CommonConfigurations.SANDWICH, 5, ItemCategory.SNACKS );

        itemDataList.add( samosa );
        itemDataList.add( bajji );
        itemDataList.add( sandwich );
        return this;

    }

    public ItemBuilder createTeaItems()
    {

        ItemData tea = new ItemData( 9, CommonConfigurations.TEA, 10, ItemCategory.TEA );
        ItemData coffee = new ItemData( 10, CommonConfigurations.COFFEE, 10, ItemCategory.TEA );
        itemDataList.add( tea );
        itemDataList.add( coffee );
        return this;

    }

    public ItemBuilder createJuiceItems()
    {

        ItemData appleJuice = new ItemData( 11, CommonConfigurations.APPLE_JUICE, 10, ItemCategory.SNACKS );
        ItemData pomoJuice = new ItemData( 12, CommonConfigurations.POMO_JUICE, 10, ItemCategory.SNACKS );
        itemDataList.add( appleJuice );
        itemDataList.add( pomoJuice );
        return this;
    }

    public List<ItemData> BuildAllCategoryItems()
    {
        this.createVegItems();
        this.createNonVegItems();
        this.createJuiceItems();
        this.createSnacksItems();
        this.createTeaItems();
        return getItemDataList();
    }

    public List<ItemData> getItemDataList()
    {
        return itemDataList;
    }

    @Override
    public String toString()
    {
        return "ItemBuilder{" +
                "itemDataList=" + itemDataList +
                '}';
    }
}
