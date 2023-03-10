package com.software.application.data.service;

import com.software.application.data.entity.*;
import com.software.application.data.entity.dto.PlaceOrderDto;
import com.software.application.data.entity.summary.OrderSummary;
import com.software.application.data.mappers.ProductMapper;
import com.software.application.data.repositories.OrderItemsRepository;
import com.software.application.data.repositories.OrderRepository;
import com.software.application.data.repositories.ProductRepository;
import com.software.application.data.service.summary.IOrder;
import com.software.application.data.service.summary.IOrderItems;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

@Service
public class OrderService implements IOrder, IOrderItems {

    private final OrderRepository orderRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public OrderService(OrderRepository orderRepository, OrderItemsRepository orderItemsRepository, ProductRepository productRepository, ProductMapper productMapper) {
        this.orderRepository = orderRepository;
        this.orderItemsRepository = orderItemsRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    private static final Set<OrderState> notAvailableStates = Collections.unmodifiableSet(
            EnumSet.complementOf(EnumSet.of(OrderState.DELIVERED, OrderState.READY, OrderState.CANCELLED)));

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll ();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Order saveOrder(User currentUser, Long id, BiConsumer<User, Order> orderFiller) {
        Order order = new Order();
        if (id == null) {
        //    order = new Order(currentUser);
        } else {
            order = load(id);
        }
   //     orderFiller.accept(currentUser, order);
        return orderRepository.save(order);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Order addOrder(Order order) {
        return orderRepository.saveAndFlush (order);
    }

    private Order load(Long id) {
        Order entity = orderRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new EntityNotFoundException ();
        }
        return entity;
    }

    @Override
    public OrderItem addProductToOrder(Long orderId, Long productId, Integer quantity) {
        Order order = orderRepository.findById(orderId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantityOnHand (quantity);

        return orderItemsRepository.save(orderItem);
    }

    @Override
    public Order addComment(User currentUser, Order order, String comment) {
        return null;
    }

    @Override
    public List<OrderSummary> findAnyMatchingStartingToday() {
        return null;
    }

    @Override
    public void placeOrder(long customerId) {
        PlaceOrderDto placeOrderDto = new PlaceOrderDto();
        placeOrderDto.setCustomerId (customerId);
        placeOrderDto.setTotalPrice(placeOrderDto.getTotalPrice ());
    }

    @Override
    public void cancelOrder(Order order) {
        orderRepository.delete (order);
    }
}
