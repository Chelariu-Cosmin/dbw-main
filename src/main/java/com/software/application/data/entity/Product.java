package com.software.application.data.entity;

import com.software.application.data.entity.enums.Availability;
import com.software.application.data.entity.enums.UM;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

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
    private BigDecimal price = BigDecimal.ZERO;

    @NotNull
    private String category = "";

    private String description;

    @Enumerated(EnumType.STRING)
    private UM unitMeasure;

    @Min(value = 0, message = "Can't have negative amount in stock")
    private int stockCount = 0;

    @NotNull
    private Availability availability = Availability.COMING;
}
