package com.sea_emperor.chef;

import com.sea_emperor.factory.ItemData;
import com.sea_emperor.model.Item;
import com.sea_emperor.preperation.ChefList;

import java.util.List;
import java.util.stream.IntStream;

//import com.sea_emperor.preperation.FoodPreperationTimeCalculator;

public interface Chef
{
    void prepareFood( Item item );

/*
    int getEstimationTimeToPrepare();
*/

    int getChefCount();

    int availableChefCountAtTime();

    void createItemList( Item item, ItemData itemData );

    List<ItemData> getListOfItemPreparedByChef();

    String getChefType();

    /*default void addItemsToQueue( Queue<Item> queue, Item item, ItemData itemData, Chef chef )
    {
        for ( int index = 0; index < item.getQuantity(); index++ )
        {
            if ( chef.availableChefCountAtTime() == 0 )
            {
                System.out.println( "Chef is not available hence purging the queue" );
                queue.clear();
            }
            queue.add( item );
            System.out.println( "items in the queue-->" + queue );
            chef.updateEstimationTime( itemData.getTimeToPrepareInMins() );
        }

    }
*/
    default void addItemsToList( List<ItemData> itemDataList, Item item, ItemData itemData )
    {
        IntStream.range( 0, item.getQuantity() ).forEach(
                itemIterate ->
                {
                    itemDataList.add( itemData );
                    System.out.println( "new item added to the list -->" + itemData.getItemName() + " Time to prepare --->" + itemData.getTimeToPrepareInMins() );
                }
        );
    }

    void setChefItemList( List<ChefList> value );

    List<ChefList> getChefItemList();

    void clearList();
}
