package com.sea_emperor.exception;

import java.util.ArrayList;

public class RestaurantSimulationExceptionBuilder
{
    private RestaurantSimulatorException restaurantSimulatorException;

    public RestaurantSimulationExceptionBuilder()
    {
        restaurantSimulatorException = new RestaurantSimulatorException( new ArrayList<>(), false, null );
    }

    public RestaurantSimulationExceptionBuilder newBuilder( ErrorCodes errorCode )
    {
        restaurantSimulatorException.setErrorCode( errorCode );
        return this;
    }

    public RestaurantSimulatorException build()
    {
        restaurantSimulatorException = new RestaurantSimulatorException( this.restaurantSimulatorException.getErrorList(), this.restaurantSimulatorException.isErrorFound(), this.restaurantSimulatorException.getErrorCode() );
        return restaurantSimulatorException;
    }

    public RestaurantSimulatorException build( String errorMessage )
    {
        restaurantSimulatorException = new RestaurantSimulatorException( this.restaurantSimulatorException.getErrorList(), this.restaurantSimulatorException.isErrorFound(), this.restaurantSimulatorException.getErrorCode(), errorMessage );
        return restaurantSimulatorException;
    }

    public RestaurantSimulationExceptionBuilder add( ErrorCodes.ContextKeys errorCode )
    {
        this.restaurantSimulatorException.add( errorCode );
        return this;
    }


    public RestaurantSimulationExceptionBuilder setErrorFound( boolean errorFound )
    {
        this.restaurantSimulatorException.setErrorFound( errorFound );
        return this;
    }

    public void buildAndthrowIfErrorFound() throws RestaurantSimulatorException
    {
        restaurantSimulatorException = new RestaurantSimulatorException( this.restaurantSimulatorException.getErrorList(), this.restaurantSimulatorException.isErrorFound(), this.restaurantSimulatorException.getErrorCode() );

        if (restaurantSimulatorException.isErrorFound()) {
            throw restaurantSimulatorException;
        }
    }
}
