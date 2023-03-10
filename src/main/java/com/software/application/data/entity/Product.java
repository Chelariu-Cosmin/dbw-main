package com.software.application.data.entity;

import com.software.application.data.entity.enums.Availability;
import com.software.application.data.entity.enums.UM;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Product implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codProduct =-1L;

    @Size(min = 2, message = "Product name must have at least two characters")
    @NotNull
    private String name = "";

    @NotNull
    @Min(0)
    private Integer price = 0;

    @NotNull
    private String category = "";

    private String description;

    @Enumerated(EnumType.STRING)
    private UM unitMeasure;

    @Min(value = 0, message = "Can't have negative amount in stock")
    private int stockCount = 0;

    @NotNull
    private Availability availability = Availability.COMING;

    public Product(String name, Integer price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product(String firstName, String lastName, int numberBetween, Double price) {
    }
}
