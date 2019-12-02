package com.sea_emperor.chef;

import com.sea_emperor.factory.ItemData;
import com.sea_emperor.model.Item;
import com.sea_emperor.model.ItemCategory;
import com.sea_emperor.preperation.ChefList;

import java.util.ArrayList;
import java.util.List;

public class VegChef implements Chef
{
    public List<ItemData> listOfItemsPreparedByVegChef;
    ItemCategory itemCategory = ItemCategory.VEG;
    public List<ChefList> chefLists;

    @Override
    public void prepareFood( Item item )
    {
        //addItemsToList( listOfItemsPreparedByVegChef, item, itemData );
        //System.out.println( getChefType() + " chef started preparing item --> " + item.getItemName() + ". Time taken to prepare the item -->" + itemData.getTimeToPrepareInMins() );
    }

   /* @Override
    public int getEstimationTimeToPrepare()
    {
        int estimationTimeToPrepareVegItems = this.foodPreperationTimeCalculator.calculate( listOfItemsPreparedByVegChef, itemCategory );
        return estimationTimeToPrepareVegItems;
    }*/

    @Override
    public int getChefCount()
    {
        return itemCategory.getChefCount();
    }

    @Override
    public int availableChefCountAtTime()
    {
        return 0;
    }

    @Override
    public String getChefType()
    {
        return itemCategory.name();
    }

    @Override
    public void setChefItemList( List<ChefList> value )
    {
        this.chefLists = chefLists;
    }

    @Override
    public List<ChefList> getChefItemList()
    {
        return null;
    }

    @Override
    public void createItemList( Item item, ItemData itemData )
    {
        listOfItemsPreparedByVegChef = new ArrayList<>();
        addItemsToList( listOfItemsPreparedByVegChef, item, itemData );
    }

    @Override
    public List<ItemData> getListOfItemPreparedByChef()
    {
        return this.listOfItemsPreparedByVegChef;
    }

    @Override
    public void clearList()
    {
        listOfItemsPreparedByVegChef = new ArrayList<>();
    }
}
