package com.sea_emperor.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCodes
{
    INVALID_REQUEST( HttpStatus.BAD_REQUEST ),
    NOT_FOUND( HttpStatus.NOT_FOUND );

    public enum ContextKeys
    {
        ITEM_NAME_NULL,
        ITEM_QUANTITY_NULL,
        ITEM_CATEGORY_NULL,
        INVALID_ITEM_ID,
        INVALID_ITEM_REQUEST,
        NO_REQUEST,
        ITEM_NOT_AVAILABLE,
        ITEM_ALREADY_AVAILABLE;
    }

    private HttpStatus httpStatus;

    ErrorCodes( HttpStatus httpStatus )
    {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus()
    {
        return httpStatus;
    }
}
