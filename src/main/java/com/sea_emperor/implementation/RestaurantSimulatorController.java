package com.sea_emperor.implementation;

import com.sea_emperor.DBOperations.DBOperations;
import com.sea_emperor.api.RestaurantSimulatorInterface;
import com.sea_emperor.chef.ChefProvider;
import com.sea_emperor.exception.ErrorCodes;
import com.sea_emperor.exception.RestaurantSimulationExceptionBuilder;
import com.sea_emperor.exception.RestaurantSimulatorException;
import com.sea_emperor.factory.ItemData;
import com.sea_emperor.model.CreateOrderRequest;
import com.sea_emperor.model.CreateOrderResponse;
import com.sea_emperor.model.Item;
import com.sea_emperor.preperation.FoodPreparationTimeCalculator;
import com.sea_emperor.preperation.FoodPreperator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping( "/restaurant" )
public class RestaurantSimulatorController implements RestaurantSimulatorInterface
{
    private static final Logger LOGGER = LoggerFactory.getLogger( RestaurantSimulatorController.class );

    @Autowired
    RestaurantSimulatorHandler restaurantSimulatorHandler;

    FoodPreperator foodPreperator;
    ChefProvider chefProvider;
    FoodPreparationTimeCalculator foodPreparationTimeCalculator;

    RestaurantSimulatorController( FoodPreperator foodPreperator, ChefProvider chefProvider, FoodPreparationTimeCalculator foodPreparationTimeCalculator )
    {
        this.foodPreperator = foodPreperator;
        this.chefProvider = chefProvider;
        this.foodPreparationTimeCalculator = foodPreparationTimeCalculator;
    }


    @Override
    public CreateOrderResponse getEstimationTime( CreateOrderRequest createOrderRequest )
    {
        CreateOrderResponse createOrderResponse = null;
        List<ItemData> itemDataList = null;

        //validate
        RestaurantSimulationExceptionBuilder restaurantSimulationExceptionBuilder = this.buildException( ErrorCodes.INVALID_REQUEST );
        this.validateCreateOrderRequest( createOrderRequest, restaurantSimulationExceptionBuilder );

        //calculationProcess
        itemDataList = this.restaurantSimulatorHandler.findAllItems();
        Pair<Date, Integer> estimatedPreperationTimePair = this.foodPreparationTimeCalculator.initiateChefItemListAndCalculatePreparationTime( itemDataList, createOrderRequest );

        //building response object
        createOrderResponse = new CreateOrderResponse( estimatedPreperationTimePair.getLeft(), estimatedPreperationTimePair.getRight() );
        return createOrderResponse;
    }

    @Override
    public DBOperations addItem( ItemData itemData )
    {
        DBOperations dbOperations;
        boolean itemExists = false;
        RestaurantSimulationExceptionBuilder restaurantSimulationExceptionBuilder = this.buildException( ErrorCodes.INVALID_REQUEST );

        this.validatorItemData( itemData, restaurantSimulationExceptionBuilder );
        itemExists = this.restaurantSimulatorHandler.validateItemExists( itemData.getItemName(), itemData.getItemCategory() );

        if (itemExists) {
            dbOperations = DBOperations.DB_OPERATION_FAILED;
            restaurantSimulationExceptionBuilder.setErrorFound( true );
            restaurantSimulationExceptionBuilder.newBuilder( ErrorCodes.INVALID_REQUEST );
            restaurantSimulationExceptionBuilder.add( ErrorCodes.ContextKeys.ITEM_ALREADY_AVAILABLE );
            restaurantSimulationExceptionBuilder.buildAndthrowIfErrorFound();
        } else {
            ItemData savedItemData = this.restaurantSimulatorHandler.addItem( itemData );
            dbOperations = ( savedItemData == null ) ? DBOperations.DB_OPERATION_FAILED : DBOperations.ITEM_ADDED_SUCCESSFULLY;
        }
        return dbOperations;
    }

    @Override
    public DBOperations createInitialmockItemData()
    {
        DBOperations dbOperations = DBOperations.ITEM_ADDED_SUCCESSFULLY;
        this.restaurantSimulatorHandler.createInitialItemsDump();
        return dbOperations;
    }

    @Override
    public List<ItemData> getAllItems()
    {
        LOGGER.info( "entered get all items" );
        List<ItemData> itemDataList = this.restaurantSimulatorHandler.findAllItems();

        LOGGER.info( "All items in the database -->", itemDataList );
        return itemDataList;
    }

    @Override
    public DBOperations deleteItem( String itemId )
    {
        LOGGER.info( "entered get all items" );
        DBOperations dbOperations = DBOperations.DB_OPERATION_FAILED;
        RestaurantSimulationExceptionBuilder restaurantSimulationExceptionBuilder = this.buildException( ErrorCodes.INVALID_REQUEST );
        this.validateStringValue( itemId, restaurantSimulationExceptionBuilder );
        int parsedItemId = this.parseStringToInteger( itemId, restaurantSimulationExceptionBuilder );
        boolean isItemAvailable = this.restaurantSimulatorHandler.validateItemIdAvailable( parsedItemId );
        if (!isItemAvailable) {
            restaurantSimulationExceptionBuilder.newBuilder( ErrorCodes.NOT_FOUND );
            restaurantSimulationExceptionBuilder.setErrorFound( true );
            restaurantSimulationExceptionBuilder.add( ErrorCodes.ContextKeys.ITEM_NOT_AVAILABLE );
            restaurantSimulationExceptionBuilder.buildAndthrowIfErrorFound();
        } else {
            dbOperations = this.restaurantSimulatorHandler.deleteItem( parsedItemId );
        }
        return dbOperations;
    }

    private int parseStringToInteger( String itemId, RestaurantSimulationExceptionBuilder restaurantSimulationExceptionBuilder )
    {
        int parsedItemId = 0;
        try {
            parsedItemId = Integer.parseInt( itemId );
        }
        catch (Exception e) {
            restaurantSimulationExceptionBuilder.setErrorFound( true );
            restaurantSimulationExceptionBuilder.add( ErrorCodes.ContextKeys.INVALID_ITEM_ID );
            RestaurantSimulatorException restaurantSimulatorException = restaurantSimulationExceptionBuilder.build( "Invalid item id" );
            throw restaurantSimulatorException;
        }
        return parsedItemId;
    }

    private RestaurantSimulationExceptionBuilder buildException( ErrorCodes errorCodes )
    {
        RestaurantSimulationExceptionBuilder restaurantSimulationExceptionBuilder = new RestaurantSimulationExceptionBuilder().newBuilder( errorCodes );
        return restaurantSimulationExceptionBuilder;
    }

    private void validateStringValue( String itemId, RestaurantSimulationExceptionBuilder restaurantSimulationExceptionBuilder )
    {
        if (StringUtils.isEmpty( itemId )) {
            restaurantSimulationExceptionBuilder.setErrorFound( true );
            restaurantSimulationExceptionBuilder.add( ErrorCodes.ContextKeys.INVALID_ITEM_ID );
        }

        restaurantSimulationExceptionBuilder.buildAndthrowIfErrorFound();
    }

    private void validatorItemData( ItemData itemData, RestaurantSimulationExceptionBuilder restaurantSimulationExceptionBuilder )
    {
        if (null == itemData || null == itemData.getItemName() || null == itemData.getItemCategory()) {
            restaurantSimulationExceptionBuilder.setErrorFound( true );
            restaurantSimulationExceptionBuilder.add( ErrorCodes.ContextKeys.INVALID_ITEM_REQUEST );
        }

        restaurantSimulationExceptionBuilder.buildAndthrowIfErrorFound();
    }

    private void validateCreateOrderRequest( CreateOrderRequest createOrderRequest, RestaurantSimulationExceptionBuilder restaurantSimulationExceptionBuilder ) throws RestaurantSimulatorException
    {
        if (null == createOrderRequest || null == createOrderRequest.getItems()) {
            restaurantSimulationExceptionBuilder.setErrorFound( true );
            restaurantSimulationExceptionBuilder.add( ErrorCodes.ContextKeys.NO_REQUEST );
        }

        this.validateItemAreAvailable( createOrderRequest.getItems(), restaurantSimulationExceptionBuilder );
        restaurantSimulationExceptionBuilder.buildAndthrowIfErrorFound();
    }

    private void validateItemAreAvailable( List<Item> items, RestaurantSimulationExceptionBuilder restaurantSimulationExceptionBuilder )
    {
        boolean AllItemExists = this.restaurantSimulatorHandler.validateAllItemsAreAvailable( items );

        if (!AllItemExists) {
            restaurantSimulationExceptionBuilder.setErrorFound( true );
            restaurantSimulationExceptionBuilder.add( ErrorCodes.ContextKeys.ITEM_NOT_AVAILABLE );
        }
    }
}
