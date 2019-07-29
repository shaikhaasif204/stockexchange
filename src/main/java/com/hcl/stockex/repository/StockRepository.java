package com.hcl.stockex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.stockex.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
