package com.sea_emperor.chef;

import com.sea_emperor.factory.ItemData;
import com.sea_emperor.model.Item;
import com.sea_emperor.model.ItemCategory;
import com.sea_emperor.preperation.ChefList;

import java.util.ArrayList;
import java.util.List;

public class NonVegChef implements Chef
{
    public List<ItemData> listOfItemsPreparedByNonVegChef;

    public List<ChefList> chefLists;

    ItemCategory itemCategory = ItemCategory.NON_VEG;

    @Override
    public void prepareFood( Item item )
    {
        //addItemsToList( listOfItemsPreparedByNonVegChef, item, itemData );
    }

    /*@Override
    public int getEstimationTimeToPrepare()
    {
        int estimationTimeToPrepareNonVegItems = this.foodPreperationTimeCalculator.calculate( listOfItemsPreparedByNonVegChef, itemCategory );
        return estimationTimeToPrepareNonVegItems;
    }*/

    @Override
    public int getChefCount()
    {
        return itemCategory.getChefCount();
    }

    @Override
    public int availableChefCountAtTime()
    {
        return 0;//ItemCategory.NON_VEG.getChefCount() - NonVegChefQueue.size();
    }

    @Override
    public String getChefType()
    {
        return itemCategory.name();
    }

    @Override
    public void setChefItemList( List<ChefList> chefLists )
    {
        this.chefLists = chefLists;
    }

    @Override
    public List<ChefList> getChefItemList()
    {
        return this.chefLists;
    }


    @Override
    public void createItemList( Item item, ItemData itemData )
    {
        listOfItemsPreparedByNonVegChef = new ArrayList<>();
        addItemsToList( listOfItemsPreparedByNonVegChef, item, itemData );
    }

    @Override
    public List<ItemData> getListOfItemPreparedByChef()
    {
        return this.listOfItemsPreparedByNonVegChef;
    }

    @Override
    public void clearList()
    {
        listOfItemsPreparedByNonVegChef = new ArrayList<>();
    }

}
