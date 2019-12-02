package com.sea_emperor.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item
{
    private String itemName;
    private int quantity;
    private ItemCategory itemCategory;

    @JsonCreator
    public Item( @JsonProperty( "itemName" ) String itemName,
                 @JsonProperty( "quantity" ) int quantity,
                 @JsonProperty( "itemCategory" ) ItemCategory itemCategory )
    {
        this.itemName = itemName;
        this.quantity = quantity;
        this.itemCategory = itemCategory;
    }

    public String getItemName()
    {
        return itemName;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public ItemCategory getItemCategory()
    {
        return itemCategory;
    }

    @Override
    public String toString()
    {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", itemCategory=" + itemCategory +
                '}';
    }

}
