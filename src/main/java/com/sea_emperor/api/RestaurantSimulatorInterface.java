package com.sea_emperor.api;

import com.sea_emperor.DBOperations.DBOperations;
import com.sea_emperor.factory.ItemData;
import com.sea_emperor.model.CreateOrderRequest;
import com.sea_emperor.model.CreateOrderResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface RestaurantSimulatorInterface
{
    @RequestMapping( method = RequestMethod.POST, value = "/getEstimatedTime" )
    CreateOrderResponse getEstimationTime( @RequestBody CreateOrderRequest createOrderRequest );

    @RequestMapping( method = RequestMethod.POST, value = "/addItem" )
    DBOperations addItem( @RequestBody ItemData itemData );

    @RequestMapping( method = RequestMethod.POST, value = "/createMock" )
    DBOperations createInitialmockItemData();

    @RequestMapping( method = RequestMethod.GET, value = "/getAllItems" )
    List<ItemData> getAllItems();

    @RequestMapping( method = RequestMethod.DELETE, value = "/{itemId}" )
    DBOperations deleteItem( @PathVariable( "itemId" ) String itemId );
}
