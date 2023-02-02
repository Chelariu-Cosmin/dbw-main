package com.software.application.data.entity;

import com.software.application.data.entity.enums.StatusStock;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Stock extends AbstractEntity {

    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_product")
    @ToString.Exclude
    private Product product;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private StatusStock statusStock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Stock stock = (Stock) o;
        return Objects.equals (name, stock.name) && Objects.equals (product, stock.product) && Objects.equals (quantity, stock.quantity) && statusStock == stock.statusStock;
    }

    @Override
    public int hashCode() {
        return Objects.hash (name, product, quantity, statusStock);
    }
}
