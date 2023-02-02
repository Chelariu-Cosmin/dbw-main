package com.software.application.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class OrderItem extends AbstractEntity {

    @ManyToOne
    private Product product;

    @Min(1)
    @NotNull
    private Integer quantityOnHand = 1;

    @Size(max = 255)
    private String comment;

    public int getTotalPrice() {
        return quantityOnHand == null || product == null ? 0 : quantityOnHand * product.getPrice ();
    }
}
