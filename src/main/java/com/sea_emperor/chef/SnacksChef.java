package com.sea_emperor.chef;

import com.sea_emperor.factory.ItemData;
import com.sea_emperor.model.Item;
import com.sea_emperor.model.ItemCategory;
import com.sea_emperor.preperation.ChefList;

import java.util.ArrayList;
import java.util.List;

public class SnacksChef implements Chef
{
    public List<ItemData> listOfItemsPreparedBySnacksChef;
    ItemCategory itemCategory = ItemCategory.SNACKS;
    public List<ChefList> chefLists;

    @Override
    public void prepareFood( Item item )
    {
        // addItemsToList( listOfItemsPreparedBySnacksChef, item, itemData );
        //System.out.println( getChefType() + " chef started preparing item --> " + item.getItemName() + ". Time taken to prepare the item -->" + itemData.getTimeToPrepareInMins() );
    }

    /*@Override
    public int getEstimationTimeToPrepare()
    {
        int estimationTimeToPrepareSnacksItems = this.foodPreperationTimeCalculator.calculate( listOfItemsPreparedBySnacksChef, itemCategory );
        return estimationTimeToPrepareSnacksItems;
    }
*/
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
    public List<ChefList> getChefItemList()
    {
        return null;
    }

    @Override
    public void createItemList( Item item, ItemData itemData )
    {
        listOfItemsPreparedBySnacksChef = new ArrayList<>();
        addItemsToList( listOfItemsPreparedBySnacksChef, item, itemData );
    }


    @Override
    public void setChefItemList( List<ChefList> chefLists )
    {
        this.chefLists = chefLists;
    }

    @Override
    public List<ItemData> getListOfItemPreparedByChef()
    {
        return this.listOfItemsPreparedBySnacksChef;
    }

    @Override
    public void clearList()
    {
        listOfItemsPreparedBySnacksChef = new ArrayList<>();
    }
}
