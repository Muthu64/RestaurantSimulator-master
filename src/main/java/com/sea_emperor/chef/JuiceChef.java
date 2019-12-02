package com.sea_emperor.chef;

import com.sea_emperor.factory.ItemData;
import com.sea_emperor.model.Item;
import com.sea_emperor.model.ItemCategory;
import com.sea_emperor.preperation.ChefList;

import java.util.ArrayList;
import java.util.List;

public class JuiceChef implements Chef
{
    private List<ItemData> listOfItemsPreparedByJuiceChef = new ArrayList();
    ItemCategory itemCategory = ItemCategory.JUICE;
    public List<ChefList> chefLists;

    @Override
    public void prepareFood( Item item )
    {
        //addItemsToList( listOfItemsPreparedByJuiceChef, item );
        System.out.println( getChefType() + " chef started preparing item --> " + item.getItemName() );
    }

/*
    @Override
    public int getEstimationTimeToPrepare()
    {
        int estimationTimeToPrepareJuiceItems = this.foodPreperationTimeCalculator.calculate( listOfItemsPreparedByJuiceChef, itemCategory );
        return estimationTimeToPrepareJuiceItems;
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
        return listOfItemsPreparedByJuiceChef.size();
    }

    @Override
    public void createItemList( Item item, ItemData itemData )
    {
        addItemsToList( listOfItemsPreparedByJuiceChef, item, itemData );
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
    public void clearList()
    {
        listOfItemsPreparedByJuiceChef = new ArrayList<>();
    }

    @Override
    public void setChefItemList( List<ChefList> chefLists )
    {
        this.chefLists = chefLists;
    }

    @Override
    public List<ItemData> getListOfItemPreparedByChef()
    {
        return this.listOfItemsPreparedByJuiceChef;
    }
}
