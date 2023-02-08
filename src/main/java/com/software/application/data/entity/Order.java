package com.software.application.data.entity;

import com.software.application.data.entity.summary.OrderSummary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "OrderInfo") // "Order" is a reserved word
public class Order extends AbstractEntity implements OrderSummary{

    @NotNull
    private Date createdDate;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @Column(name = "total_price")
    private Integer totalPrice;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id",referencedColumnName = "id",insertable = false,updatable = false)
    private List<OrderItem> items;

  //  private OrderState state;

    @Override
    public Integer getTotalPrice() {
//        return items.stream ()
//                    .map (OrderItem::getTotalPrice)
//                    .reduce (0, Integer::sum);
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass (this) != Hibernate.getClass (o)) return false;
        Order order = (Order) o;
        return getId () != null && Objects.equals (getId (), order.getId ());
    }

    @Override
    public int hashCode() {
        return getClass ().hashCode ();
    }
}
