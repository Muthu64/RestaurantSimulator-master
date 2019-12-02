package com.sea_emperor.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestaurantSimulatorExceptionHandler extends ResponseEntityExceptionHandler
{
    @SuppressWarnings( {"unchecked", "rawtypes"} )
    @ExceptionHandler( RestaurantSimulatorException.class )
    public final ResponseEntity<Object> handleAllExceptions( RestaurantSimulatorException restaurantSimulatorException )
    {
        System.out.println( "Error Happened---->" + restaurantSimulatorException.getErrorCode() + "-----" + restaurantSimulatorException.getErrorList() );
        GernericErrorResponse genericErrorResponse = new GernericErrorResponse( restaurantSimulatorException.getErrorList(), restaurantSimulatorException.isErrorFound(), restaurantSimulatorException.getErrorCode() );
        return new ResponseEntity( genericErrorResponse, null, restaurantSimulatorException.getErrorCode().getHttpStatus() );
    }
}
