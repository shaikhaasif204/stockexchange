package com.hcl.stockex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.stockex.entity.StockTransaction;

@Repository
public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long>{

	List<StockTransaction> findAllByUserId(Long userId);

}
