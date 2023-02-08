package com.software.application.data.service.summary;

import com.software.application.data.entity.OrderItem;

public interface IOrderItems {
    OrderItem addProductToOrder(Long orderId, Long productId, Integer quantity);
}
