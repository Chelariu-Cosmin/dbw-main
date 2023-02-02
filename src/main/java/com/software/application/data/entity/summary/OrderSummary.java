package com.software.application.data.entity.summary;

import com.software.application.data.entity.Customer;
import com.software.application.data.entity.OrderItem;
import com.software.application.data.entity.OrderState;
import com.software.application.data.entity.PickupLocation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface OrderSummary {

    Long getId();

    OrderState getState();

    Customer getCustomer();

    List<OrderItem> getItems();

    LocalDate getDueDate();

    LocalTime getDueTime();

    PickupLocation getPickupLocation();

    Integer getTotalPrice();
}
