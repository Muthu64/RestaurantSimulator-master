package com.sea_emperor.chef;

import com.sea_emperor.factory.ItemData;
import com.sea_emperor.model.Item;
import com.sea_emperor.model.ItemCategory;
import com.sea_emperor.preperation.ChefList;

import java.util.ArrayList;
import java.util.List;

public class TeaChef implements Chef
{
    public List<ItemData> listOfItemsPreparedByTeaChef;
    ItemCategory itemCategory = ItemCategory.TEA;
    public List<ChefList> chefLists;

    @Override
    public void prepareFood( Item item )
    {
        //addItemsToList( listOfItemsPreparedByTeaChef, item, itemData );
        //System.out.println( getChefType() + " chef started preparing item --> " + item.getItemName() + ". Time taken to prepare the item -->" + itemData.getTimeToPrepareInMins() );
    }

    /*@Override
    public int getEstimationTimeToPrepare()
    {

        int estimationTimeToPrepareTeaItems = this.foodPreperationTimeCalculator.calculate( listOfItemsPreparedByTeaChef, itemCategory );
        return estimationTimeToPrepareTeaItems;

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
    public List<ChefList> getChefItemList()
    {
        return null;
    }


    @Override
    public void createItemList( Item item, ItemData itemData )
    {
        listOfItemsPreparedByTeaChef = new ArrayList<>();
        addItemsToList( listOfItemsPreparedByTeaChef, item, itemData );
    }

    @Override
    public void setChefItemList( List<ChefList> chefLists )
    {
        this.chefLists = chefLists;
    }

    @Override
    public List<ItemData> getListOfItemPreparedByChef()
    {
        return this.listOfItemsPreparedByTeaChef;
    }


    @Override
    public void clearList()
    {
        listOfItemsPreparedByTeaChef = new ArrayList<>();
    }
}

