package com.sea_emperor.exception;

import java.util.List;

public class RestaurantSimulatorException extends RuntimeException
{
    private List<ErrorCodes.ContextKeys> errorList;
    private boolean errorFound;
    private String errorMessage;
    private ErrorCodes errorCode;

    public RestaurantSimulatorException( List<ErrorCodes.ContextKeys> errorList, boolean errorFound, ErrorCodes errorCode )
    {
        this.errorList = errorList;
        this.errorFound = errorFound;
        this.errorCode = errorCode;
    }

    public RestaurantSimulatorException( List<ErrorCodes.ContextKeys> errorList, boolean errorFound, ErrorCodes errorCode, String errorMessage )
    {
        super( errorMessage );
        this.errorList = errorList;
        this.errorFound = errorFound;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public void add( ErrorCodes.ContextKeys value )
    {
        this.errorList.add( value );
    }

    public List<ErrorCodes.ContextKeys> getErrorList()
    {
        return errorList;
    }

    public void setErrorList( List<ErrorCodes.ContextKeys> errorList )
    {
        this.errorList = errorList;
    }

    public ErrorCodes getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode( ErrorCodes errorCode )
    {
        this.errorCode = errorCode;
    }

    public boolean isErrorFound()
    {
        return errorFound;
    }

    public void setErrorFound( boolean errorFound )
    {
        this.errorFound = errorFound;
    }


}
