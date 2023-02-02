package com.software.application.data.service;

import com.software.application.data.entity.Stock;
import com.software.application.data.repositories.StockRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.Optional;

@Service
public class StockService implements CrudListener<Stock> {

    private final StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }

    public Optional<Stock> get(Long id) {
        return repository.findById(id);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Stock> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Stock> list(Pageable pageable, Specification<Stock> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

    @Override
    public Collection<Stock> findAll() {
        return repository.findAll ();
    }

    @Override
    public Stock add(Stock product) {
        return repository.save (product);
    }

    @Override
    public Stock update(Stock product) {
        return repository.save (product);
    }

    @Override
    public void delete(Stock product) {
        repository.delete (product);
    }
}
