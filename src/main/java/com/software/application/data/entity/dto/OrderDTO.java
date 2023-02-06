package com.software.application.data.entity.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;

    @NotNull
    private Date createdDate;

    @Column(name = "total_price")
    private Integer totalPrice;

    private @NotBlank Long customerId;

    private List<OrderItemsDto> items;
}
