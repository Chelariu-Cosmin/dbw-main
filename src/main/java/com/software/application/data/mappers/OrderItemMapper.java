package com.software.application.data.mappers;

import com.software.application.data.entity.OrderItem;
import com.software.application.data.entity.dto.OrderItemsDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    private final ModelMapper modelMapper;

    public OrderItemMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderItemsDto toDto(OrderItem orderItem){
        modelMapper.getConfiguration()
                   .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(orderItem, OrderItemsDto.class);

    }

    public OrderItem toOrderItem(OrderItemsDto orderItemsDto){
        modelMapper.getConfiguration()
                   .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(orderItemsDto, OrderItem.class);
    }
}
