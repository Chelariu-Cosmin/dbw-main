package com.software.application.data.service.summary;

import com.software.application.data.entity.Order;
import com.software.application.data.entity.User;
import com.software.application.data.entity.summary.OrderSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public interface IOrder {

    Order saveOrder(User currentUser, Long id, BiConsumer<User, Order> orderFiller);

    Order saveOrder(Order order);

    Order addComment(User currentUser, Order order, String comment);


    Page<Order> findAnyMatchingAfterDueDate(Optional<String> optionalFilter,
                                            Optional<LocalDate> optionalFilterDate, Pageable pageable);

    List<OrderSummary> findAnyMatchingStartingToday();


}
