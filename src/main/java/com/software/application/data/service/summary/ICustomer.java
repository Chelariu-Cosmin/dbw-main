package com.software.application.data.service.summary;

import com.software.application.data.entity.dto.CustomerDTO;

import java.util.List;

public interface ICustomer {

    CustomerDTO getCustomerById(Long id);

    void addCustomer(CustomerDTO customerDTO);

    void deleteCustomer(CustomerDTO customerDTO);

    int count();

     List<CustomerDTO> findAll();
}
