package com.software.application.data.entity;

import com.software.application.data.entity.enums.UM;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Product {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codProduct;

    @NotNull
    private String name = "";

    @NotNull
    private Integer price = 0;

    @NotNull
    private String category = "";

    private String description;

    @Enumerated(EnumType.STRING)
    private UM unitMeasure;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Stock stock;
}
