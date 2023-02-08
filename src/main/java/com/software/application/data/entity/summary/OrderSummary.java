package com.software.application.data.entity.summary;

import com.software.application.data.entity.Customer;
import com.software.application.data.entity.OrderItem;

import java.util.List;

public interface OrderSummary {

    Long getId();

 //   OrderState getState();

    Customer getCustomer();

    List<OrderItem> getItems();

    Integer getTotalPrice();
}
