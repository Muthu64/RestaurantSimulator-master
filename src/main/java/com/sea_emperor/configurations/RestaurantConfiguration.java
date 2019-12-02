package com.sea_emperor.configurations;

import com.sea_emperor.chef.ChefProvider;
import com.sea_emperor.preperation.FoodPreparationTimeCalculator;
import com.sea_emperor.preperation.FoodPreperator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantConfiguration
{
    @Bean
    public ChefProvider chefProvider()
    {
        ChefProvider chefProvider = new ChefProvider();
        System.out.println( "created bean-->" + chefProvider );
        return new ChefProvider();
    }

    @Bean
    public FoodPreperator foodPreperator( ChefProvider chefProvider )
    {
        FoodPreperator foodPreperator = new FoodPreperator( chefProvider );
        System.out.println( "created bean-->" + foodPreperator );
        return foodPreperator;
    }

    @Bean
    FoodPreparationTimeCalculator foodPreparationTimeCalculator( ChefProvider chefProvider )
    {
        FoodPreparationTimeCalculator foodPreparationTimeCalculator = new FoodPreparationTimeCalculator( chefProvider );
        System.out.println( "created bean-->" + foodPreparationTimeCalculator );
        return foodPreparationTimeCalculator;
    }
}
