package com.sea_emperor.preperation;

import com.sea_emperor.factory.ItemData;
import com.sea_emperor.model.CreateOrderRequest;
import com.sea_emperor.model.ItemCategory;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Date;
import java.util.List;

public interface FoodPreperationTimeCalculatorInterface
{
    int calculate( List<ItemData> itemDataList, ItemCategory itemCategory );

    Pair<Date, Integer> initiateChefItemListAndCalculatePreparationTime( List<ItemData> itemDataList, CreateOrderRequest createOrderRequest );


}
