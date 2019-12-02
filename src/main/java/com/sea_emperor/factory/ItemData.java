package com.sea_emperor.factory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sea_emperor.model.ItemCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "itemData" )
public class ItemData
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "item_id" )
    private int id;

    @Column( name = "item_name" )
    private String itemName;

    @Column( name = "time_to_prepare" )
    private int timeToPrepareInMins;

    @Enumerated( EnumType.STRING )
    @Column( name = "item_category" )
    private ItemCategory itemCategory;

    public ItemData()
    {

    }

    @JsonCreator
    public ItemData( @JsonProperty( "itemId" ) int id, @JsonProperty( "itemName" ) String itemName, @JsonProperty( "timeToPrepareInMins" ) int timeToPrepareInMins, @JsonProperty( "itemCategory" ) ItemCategory itemCategory )
    {
        this.id = id;
        this.itemName = itemName;
        this.timeToPrepareInMins = timeToPrepareInMins;
        this.itemCategory = itemCategory;
    }

    public int getId()
    {
        return id;
    }

    public String getItemName()
    {
        return itemName;
    }

    public int getTimeToPrepareInMins()
    {
        return timeToPrepareInMins;
    }

    public ItemCategory getItemCategory()
    {
        return itemCategory;
    }

    @Override
    public String toString()
    {
        return "itemName='" + itemName + '\'' +
                ", timeToPrepareInMins=" + timeToPrepareInMins +
                ", itemCategory=" + itemCategory;
    }
}
