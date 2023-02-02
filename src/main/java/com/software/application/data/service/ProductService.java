package com.software.application.data.service;

import com.software.application.data.dto.ProductDTO;
import com.software.application.data.entity.Product;
import com.software.application.data.mappers.ProductMapper;
import com.software.application.data.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProduct {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll().stream ().map (productMapper::toDto)
                                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDto) {

         productRepository.save (productMapper.toProduct(productDto));

        return null;
    }

    @Override
    public ProductDTO getProductById(Long id) throws NoSuchElementException {
       return productRepository
                .findById (id)
                .map (productMapper::toDto)
                .orElseThrow (NoSuchElementException::new);
    }


    @Override
    public ProductDTO update(ProductDTO productDto) {
        Optional<Product> oProduct = productRepository.findById(productDto.getCodProduct ());

        if (oProduct.isEmpty())
            throw new NoSuchElementException(" Product with id " + productDto.getCodProduct () + " does not exist");

         productRepository.save (productMapper.toProduct(productDto));

         return null;
    }

    @Override
    public ProductDTO delete(ProductDTO productDTO) {

        productRepository.delete(productMapper.toProduct (productDTO));

        return null;
    }

    @Override
    public Page<Product> list(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public int count() {
        return (int) productRepository.count();
    }

    @Override
    public List<ProductDTO> findByNameContainingIgnoreCase(String name) throws NoSuchElementException {
        return productRepository
                .findByNameContainingIgnoreCase(name)
                .stream().map (productMapper::toDto)
                .collect(Collectors.toList());
    }
}
