package com.sea_emperor.preperation;

import com.sea_emperor.chef.Chef;
import com.sea_emperor.chef.ChefProvider;
import com.sea_emperor.factory.ItemData;
import com.sea_emperor.factory.ItemFactory;
import com.sea_emperor.model.CreateOrderRequest;
import com.sea_emperor.model.Item;
import com.sea_emperor.model.ItemCategory;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class FoodPreperator
{
    private ChefProvider chefProvider;
    List<ItemData> itemDataList;
    Set<ItemCategory> itemCategorySet;

    public FoodPreperator( ChefProvider chefProvider )
    {
        Objects.requireNonNull( chefProvider, "chefProvider must not be null" );

        this.chefProvider = chefProvider;
        this.itemCategorySet = new LinkedHashSet<>();
        itemDataList = ItemFactory.getItemDataList();
    }

    public Date prepareOrder( CreateOrderRequest createOrderRequest )
    {
        Queue<Chef> chefQueue;
        //Filter Item Category
        itemCategorySet = createOrderRequest.getItems().stream().map( Item::getItemCategory ).collect( Collectors.toSet() );

        for ( ItemCategory itemCategory : itemCategorySet ) {
            List<ChefList> chefLists = chefProvider.getChef( itemCategory ).getChefItemList();
            ExecutorService executorService = Executors.newFixedThreadPool( itemCategory.getChefCount() );

            chefLists.stream().forEach( chefList ->
            {
                executorService.execute( chefList );
            } );
            //executorService.shutdown();
        }
        return null;
    }

    private void addItemsToChefQueue( Item item, ItemData itemData )
    {
        Chef chef = chefProvider.getChef( item.getItemCategory() );
        chef.createItemList( item, itemData );
    }

    private void assignTaskToChef( Item item, ItemData itemData )
    {
        Chef chef = chefProvider.getChef( item.getItemCategory() );
        chef.prepareFood( item );
    }

    private ItemData mapItemData( Item item )
    {
        ItemData filteredItem = itemDataList.stream().filter( itemData -> itemData.getItemName().equals( item.getItemName() ) && itemData.getItemCategory() == item.getItemCategory() ).findFirst().get();

        return filteredItem;
    }
}
