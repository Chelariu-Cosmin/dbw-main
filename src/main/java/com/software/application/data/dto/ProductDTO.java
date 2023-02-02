package com.software.application.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.software.application.data.entity.enums.UM;
import lombok.Data;

@Data
public class ProductDTO {

    @JsonProperty(value = "COD_PRODUCT")
    private Long codProduct;

    @JsonProperty(value = "NAME")
    private String name;

    @JsonProperty(value = "PRICE")
    private Integer price;

    @JsonProperty(value = "CATEGORY")
    private String category;

    @JsonProperty(value = "DESCRIPTION")
    private String description;

    @JsonProperty(value = "unitMeasure")
    private UM unitMeasure;
}
