package com.software.application.data.service;

import com.software.application.data.entity.Product;
import com.software.application.data.entity.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProduct {

    List<ProductDTO> findAllProducts();

    ProductDTO addProduct(ProductDTO productDto);

    ProductDTO getProductById(Long id);

    ProductDTO update(ProductDTO productDto);

    ProductDTO delete(ProductDTO productDTO);

    Page<Product> list(Pageable pageable);

    int count();

    List<ProductDTO> findByNameContainingIgnoreCase(String name);
}
