package com.sea_emperor.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class CreateOrderRequest
{
    private List<Item> items;

    private Date orderTime;

    public CreateOrderRequest( List<Item> items, Date orderTime )
    {
        this.items = items;
        this.orderTime = orderTime;
    }

    @JsonCreator
    public CreateOrderRequest( @JsonProperty( "items" ) List<Item> items )
    {
        this( items, new Date() );
    }

    public List<Item> getItems()
    {
        return items;
    }

    public Date getOrderTime()
    {
        return orderTime;
    }

    @Override
    public String toString()
    {
        return "CreateOrderRequest{" +
                "items=" + items +
                ", orderTime=" + orderTime +
                '}';
    }
}
