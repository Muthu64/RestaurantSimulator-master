package com.sea_emperor.exception;

import java.util.List;

public class GernericErrorResponse
{
    private List<ErrorCodes.ContextKeys> errorList;

    private boolean errorFound;

    private ErrorCodes errorCode;

    public GernericErrorResponse( List<ErrorCodes.ContextKeys> errorList, boolean errorFound, ErrorCodes errorcode )
    {
        this.errorCode = errorcode;
        this.errorList = errorList;
        this.errorFound = errorFound;
    }

    public List<ErrorCodes.ContextKeys> getErrorList()
    {
        return errorList;
    }

    public void setErrorList( List<ErrorCodes.ContextKeys> errorList )
    {
        this.errorList = errorList;
    }

    public boolean isErrorFound()
    {
        return errorFound;
    }

    public void setErrorFound( boolean errorFound )
    {
        this.errorFound = errorFound;
    }

    public ErrorCodes getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode( ErrorCodes errorCode )
    {
        this.errorCode = errorCode;
    }


}
