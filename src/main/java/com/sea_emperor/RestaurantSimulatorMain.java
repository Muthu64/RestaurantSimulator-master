package com.sea_emperor;

import com.sea_emperor.configurations.RestaurantConfiguration;
import com.sea_emperor.repository.ItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import( RestaurantConfiguration.class )
@EnableJpaRepositories( basePackageClasses = ItemRepository.class )
public class RestaurantSimulatorMain
{
    public static void main( String[] args )
    {
        SpringApplication.run( RestaurantSimulatorMain.class, args );
    }
}
