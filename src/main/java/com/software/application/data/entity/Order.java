package com.software.application.data.entity;

import com.software.application.data.entity.summary.OrderSummary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "OrderInfo") // "Order" is a reserved word
@NamedEntityGraphs({@NamedEntityGraph(name = Order.ENTITY_GRAPTH_BRIEF, attributeNodes = {
        @NamedAttributeNode("customer"),
        @NamedAttributeNode("pickupLocation")
}), @NamedEntityGraph(name = Order.ENTITY_GRAPTH_FULL, attributeNodes = {
        @NamedAttributeNode("customer"),
        @NamedAttributeNode("pickupLocation"),
        @NamedAttributeNode("items"),
        @NamedAttributeNode("history")
})})
@Table(indexes = @Index(columnList = "dueDate"))
public class Order extends AbstractEntity implements OrderSummary {

    public static final String ENTITY_GRAPTH_BRIEF = "Order.brief"; //short
    public static final String ENTITY_GRAPTH_FULL = "Order.full";

    @NotNull
    private LocalDate dueDate;

    @NotNull
    private LocalTime dueTime;

    @NotNull
    @ManyToOne
    private PickupLocation pickupLocation;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @OrderColumn
    @JoinColumn
    @BatchSize(size = 1000)
    @NotEmpty
    @Valid
    private List<OrderItem> items;

    @NotNull
    private OrderState state;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderColumn
    @JoinColumn
    private List<HistoryItem> history;

    public Order(User createdBy) {
        this.state = OrderState.NEW;
        setCustomer (new Customer ());
        addHistoryItem (createdBy, "Order placed");
        this.items = new ArrayList<> ();
    }

    public void addHistoryItem(User createdBy, String comment) {
        HistoryItem item = new HistoryItem (createdBy, comment);
        item.setNewState (state);
        if (history == null) {
            history = new LinkedList<> ();
        }
        history.add (item);
    }

    @Override
    public OrderState getState() {
        return state;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public List<OrderItem> getItems() {
        return items;
    }

    @Override
    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public LocalTime getDueTime() {
        return dueTime;
    }

    @Override
    public PickupLocation getPickupLocation() {
        return pickupLocation;
    }

    @Override
    public Integer getTotalPrice() {
        return items.stream ()
                    .map (OrderItem::getTotalPrice)
                    .reduce (0, Integer::sum);
    }

    @Override
    public String toString() {
        return "Order{" + "dueDate=" + dueDate + ", dueTime=" + dueTime + ", pickupLocation=" + pickupLocation
                + ", customer=" + customer + ", items=" + items + ", state=" + state + '}';
    }

    //update status by user
    public void changeState(User user, OrderState state) {
        boolean createHistory = this.state != state && this.state != null && state != null;
        this.state = state;
        if (createHistory) {
            addHistoryItem (user, "Order " + state);
        }
    }
}
