package com.software.application.data.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.software.application.data.entity.enums.Availability;
import com.software.application.data.entity.enums.UM;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    @JsonProperty(value = "COD_PRODUCT")
    private Long codProduct;

    @JsonProperty(value = "NAME")
    private String name;

    @JsonProperty(value = "PRICE")
    private BigDecimal price = BigDecimal.ZERO;

    @JsonProperty(value = "CATEGORY")
    private String category;

    @JsonProperty(value = "DESCRIPTION")
    private String description;

    @JsonProperty(value = "UNIT_MEASURE")
    private UM unitMeasure = UM.BUG;

    @JsonProperty(value = "STOCK_COUNT")
    private int stockCount = 0;

    private Availability availability = Availability.COMING;

}
