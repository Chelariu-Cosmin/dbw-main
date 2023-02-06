package com.software.application.data.service.summary;

import com.software.application.data.entity.Order;
import com.software.application.data.entity.User;
import com.software.application.data.entity.summary.OrderSummary;

import java.util.List;
import java.util.function.BiConsumer;

public interface IOrder {

    Order saveOrder(User currentUser, Long id, BiConsumer<User, Order> orderFiller);

    Order addOrder(Order order);

    Order addComment(User currentUser, Order order, String comment);

    List<OrderSummary> findAnyMatchingStartingToday();

    void placeOrder(long customerId);
}
