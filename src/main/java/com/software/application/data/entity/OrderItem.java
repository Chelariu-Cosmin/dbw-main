package com.software.application.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

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

}
