package com.sea_emperor.preperation;

import com.sea_emperor.chef.Chef;
import com.sea_emperor.chef.ChefProvider;
import com.sea_emperor.factory.ItemData;
import com.sea_emperor.model.CreateOrderRequest;
import com.sea_emperor.model.Item;
import com.sea_emperor.model.ItemCategory;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FoodPreparationTimeCalculator implements FoodPreperationTimeCalculatorInterface
{
    private ChefProvider chefProvider;
    private Set<ItemCategory> itemCategorySet;

    public FoodPreparationTimeCalculator( ChefProvider chefProvider )
    {
        this.chefProvider = chefProvider;
        this.itemCategorySet = new HashSet<>();
    }

    @Override
    public Pair<Date, Integer> initiateChefItemListAndCalculatePreparationTime( List<ItemData> itemDataList, CreateOrderRequest createOrderRequest )
    {
        for ( Item item : createOrderRequest.getItems() ) {
            ItemData filteredItem = this.mapItemData( item, itemDataList );
            this.addItemsToChefQueue( item, filteredItem );
            itemCategorySet.add( item.getItemCategory() );
        }

        int preparationTime = this.calculateAllCategoryPreparationTime( itemCategorySet );
        System.out.println( "Over All time to prepare = " + preparationTime );


        Date updatedTime = DateUtils.addMinutes( createOrderRequest.getOrderTime(), preparationTime );
        Pair<Date, Integer> dateMinutesPair = Pair.of( updatedTime, preparationTime );
        System.out.println( "Food ordered on ->" + createOrderRequest.getOrderTime() );
        System.out.println( "Estimated delivery time ->" + updatedTime );

        return dateMinutesPair;
    }

    @Override
    public int calculate( List<ItemData> itemDataList, ItemCategory itemCategory )
    {
        ChefList chefList;
        List<ChefList> chefLists = new ArrayList<>();

        for ( ItemData itemData : itemDataList ) {
            if (( itemCategory.getChefCount() - chefLists.size() ) > 0) {
                chefList = new ChefList( new ArrayList<>() );
                chefList.addItemToTheList( itemData );
                chefLists.add( chefList );
            } else {
                chefList = chefLists.stream().reduce( ( chefList1, chefList2 ) -> ( chefList1.getSumOfItemsInList() < chefList2.getSumOfItemsInList() ) ? chefList1 : chefList2 ).get();
                chefList.addItemToTheList( itemData );
            }
        }

        chefProvider.getChef( itemCategory ).setChefItemList( chefLists );
        return chefLists.stream().reduce( ( chefList1, chefList2 ) -> ( chefList1.getSumOfItemsInList() > chefList2.getSumOfItemsInList() ) ? chefList1 : chefList2 ).get().getSumOfItemsInList();
    }

    private ItemData mapItemData( Item item, List<ItemData> itemDataList )
    {
        ItemData filteredItem = itemDataList.stream().filter( itemData -> itemData.getItemName().equals( item.getItemName() ) && itemData.getItemCategory() == item.getItemCategory() ).findFirst().get();

        return filteredItem;
    }

    private void addItemsToChefQueue( Item item, ItemData itemData )
    {
        Chef chef = chefProvider.getChef( item.getItemCategory() );
        chef.createItemList( item, itemData );
    }

    private int calculateAllCategoryPreparationTime( Set<ItemCategory> itemCategorySet )
    {
        int estimationTimeToPrepare = 0;
        for ( ItemCategory itemCategory : itemCategorySet ) {
            Chef chef = chefProvider.getChef( itemCategory );
            estimationTimeToPrepare = estimationTimeToPrepare + this.calculate( chef.getListOfItemPreparedByChef(), itemCategory );
        }
        return estimationTimeToPrepare;
    }

}
