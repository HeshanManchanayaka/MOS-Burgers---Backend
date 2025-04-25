package edu.icet.config;

import edu.icet.dto.OrderDetails;
import edu.icet.entity.OrderDetailsEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ModelMapper getMapper(){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<OrderDetailsEntity, OrderDetails>() {

            @Override
            protected void configure(){
                map().setOrderId(source.getId().getOrderId());
                map().setProductId(source.getId().getItemCode());
            }
        });


        return modelMapper;

    }
}
