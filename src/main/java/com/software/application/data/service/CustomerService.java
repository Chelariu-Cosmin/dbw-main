package com.software.application.data.service;

import com.software.application.data.entity.dto.CustomerDTO;
import com.software.application.data.mappers.CustomerMapper;
import com.software.application.data.repositories.CustomerRepository;
import com.software.application.data.service.summary.ICustomer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerService implements ICustomer {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

//    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
//        this.customerRepository = customerRepository;
//        this.customerMapper = customerMapper;
//    }

    public CustomerService() {
    }


    @Override
    public CustomerDTO getCustomerById(Long id) throws NoSuchElementException {
        return customerRepository
                .findById (id)
                .map (customerMapper::toDto)
                .orElseThrow (NoSuchElementException::new);
    }

    @Override
    public void addCustomer(CustomerDTO customerDTO) {

        if (customerDTO == null) {
            log.error ("Customer is null. Are you sure you have connected your form to the application?");
            return;
        }
        customerRepository.save (customerMapper.toCustomer (customerDTO));
    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) {
        customerRepository.delete (customerMapper.toCustomer (customerDTO));
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll ()
                                 .stream ()
                                 .map (customerMapper::toDto)
                                 .collect (Collectors.toList ());
    }
}
