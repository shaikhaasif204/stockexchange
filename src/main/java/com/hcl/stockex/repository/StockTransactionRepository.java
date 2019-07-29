package com.hcl.stockex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.stockex.entity.StockTransaction;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long>{

}
