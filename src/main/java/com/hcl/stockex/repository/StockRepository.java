package com.hcl.stockex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.stockex.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
