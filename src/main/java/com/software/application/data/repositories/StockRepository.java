package com.software.application.data.repositories;

import com.software.application.data.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StockRepository extends JpaRepository<Stock, Long>, JpaSpecificationExecutor<Stock> {

}
