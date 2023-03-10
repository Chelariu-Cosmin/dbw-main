package com.software.application.data.mappers;

import com.software.application.data.entity.Order;
import com.software.application.data.entity.dto.OrderDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final ModelMapper modelMapper;

    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderDTO toDto(Order order){
        modelMapper.getConfiguration()
                   .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(order, OrderDTO.class);

    }

    public Order toOrder(OrderDTO orderDTO){
        modelMapper.getConfiguration()
                   .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(orderDTO, Order.class);
    }
}
