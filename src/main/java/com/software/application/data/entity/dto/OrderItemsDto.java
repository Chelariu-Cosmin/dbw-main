package com.software.application.data.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderItemsDto {

    private @NotNull int price;
    private @NotNull int quantity;
    private @NotNull long orderId;
    private @NotNull long productId;

    public OrderItemsDto(int price, int quantity, long orderId, long productId) {
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }
}
