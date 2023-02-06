package com.software.application.data.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PlaceOrderDto {

    private Integer id;
    private @NotNull Long customerId;
    private @NotNull Integer totalPrice;

    public PlaceOrderDto(Integer id, Long customerId, Integer totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
    }
}
