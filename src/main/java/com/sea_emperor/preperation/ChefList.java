package com.sea_emperor.preperation;

import com.sea_emperor.factory.ItemData;

import java.util.List;

public class ChefList implements Runnable
{
    int sumOfItemsInList = 0;
    List<ItemData> listOfItems;

    public ChefList( List<ItemData> listOfItems )
    {
        this.listOfItems = listOfItems;
    }

    public void addItemToTheList( ItemData itemData )
    {
        this.listOfItems.add( itemData );
        this.sumOfItemsInList = sumOfItemsInList + itemData.getTimeToPrepareInMins();
    }

    public int getSumOfItemsInList()
    {
        return sumOfItemsInList;
    }

    public List<ItemData> getListOfItems()
    {
        return listOfItems;
    }

    @Override
    public void run()
    {
        if (this.listOfItems.isEmpty() == false) {
            listOfItems.stream().forEach( item ->
            {
                System.out.println( "preparing item===>" + item.getItemName() );
                try {
                    Thread.sleep( 10000 );
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println( "done preparing item===>" + item.getItemName() );
            } );
        } else {
            System.out.println( "No items in the queue" );
        }
    }

}
