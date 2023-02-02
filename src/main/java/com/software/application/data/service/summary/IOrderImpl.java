package com.software.application.data.service.summary;

import com.software.application.data.entity.Order;
import com.software.application.data.entity.OrderState;
import com.software.application.data.entity.User;
import com.software.application.data.entity.summary.OrderSummary;
import com.software.application.data.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.function.BiConsumer;

@Service
public class IOrderImpl implements IOrder {

    private final OrderRepository orderRepository;

    public IOrderImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private static final Set<OrderState> notAvailableStates = Collections.unmodifiableSet(
            EnumSet.complementOf(EnumSet.of(OrderState.DELIVERED, OrderState.READY, OrderState.CANCELLED)));

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Order saveOrder(User currentUser, Long id, BiConsumer<User, Order> orderFiller) {
        Order order;
        if (id == null) {
            order = new Order(currentUser);
        } else {
            order = load(id);
        }
        orderFiller.accept(currentUser, order);
        return orderRepository.save(order);
    }

    private Order load(Long id) {
        Order entity = orderRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new EntityNotFoundException ();
        }
        return entity;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Order saveOrder(Order order) {
        return orderRepository.save (order);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Order addComment(User currentUser, Order order, String comment) {
        order.addHistoryItem(currentUser, comment);
        return orderRepository.save(order);
    }

    @Override
    public Page<Order> findAnyMatchingAfterDueDate(Optional<String> optionalFilter, Optional<LocalDate> optionalFilterDate, Pageable pageable) {
        return null;
    }

    @Override
    public List<OrderSummary> findAnyMatchingStartingToday() {
        return null;
    }
}
