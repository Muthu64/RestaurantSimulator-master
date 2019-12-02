package com.sea_emperor.model;

import java.util.Date;

public class CreateOrderResponse
{
    Date estimatedFoodCompletionTime;
    Integer timeInMinutes;

    public CreateOrderResponse( Date estimatedFoodCompletionTime, Integer timeInMinutes )
    {
        this.estimatedFoodCompletionTime = estimatedFoodCompletionTime;
        this.timeInMinutes = timeInMinutes;
    }

    public CreateOrderResponse( Date estimatedFoodCompletionTime )
    {
        this.estimatedFoodCompletionTime = estimatedFoodCompletionTime;
    }

    public Date getEstimatedFoodCompletionTime()
    {
        return estimatedFoodCompletionTime;
    }

    public Integer getTimeInMinutes()
    {
        return timeInMinutes;
    }
}
