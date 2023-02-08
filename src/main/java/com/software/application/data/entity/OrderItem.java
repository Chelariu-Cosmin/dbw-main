package com.software.application.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderItem extends AbstractEntity {

    @Column(name = "codProduct")
    private Long codProduct;

    @OneToOne
    @JoinColumn(name = "codProduct",referencedColumnName = "codProduct",insertable = false,updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Order order;

    @Min(1)
    @NotNull
    private @NotNull Integer quantityOnHand = 1;

    @Column(name = "price")
    private @NotNull Integer price;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "created_date")
    private Date createdDate;

    public int getTotalPrice() {
        return quantityOnHand == null || product == null ? 0 : quantityOnHand * product.getPrice ();
    }

    public OrderItem(Long codProduct, Integer quantityOnHand, Integer price, Integer orderId, Date createdDate) {
        this.codProduct = codProduct;
        this.quantityOnHand = quantityOnHand;
        this.price = price;
        this.orderId = orderId;
        this.createdDate = new Date ();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass (this) != Hibernate.getClass (o)) return false;
        OrderItem orderItem = (OrderItem) o;
        return getId () != null && Objects.equals (getId (), orderItem.getId ());
    }

    @Override
    public int hashCode() {
        return getClass ().hashCode ();
    }
}
