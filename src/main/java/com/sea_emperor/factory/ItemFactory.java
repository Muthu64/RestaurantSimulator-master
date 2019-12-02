package com.sea_emperor.factory;

import java.util.List;

public class ItemFactory
{
    private static List<ItemData> itemDataList;

    public static List<ItemData> buildAndGetItems()
    {
        ItemBuilder itemBuilder = new ItemBuilder();
        itemDataList = itemBuilder.BuildAllCategoryItems();
        System.out.println( itemDataList );
        return itemDataList;
    }

    public static List<ItemData> getItemDataList()
    {
        return itemDataList;
    }
}
