package com.hcl.stockex.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.stockex.entity.StockTransaction;

@Repository
public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long>{

	@Query(value="select * from stock_transaction where user_id=:userId and status=:requestStatus",nativeQuery = true)
	List<StockTransaction> findAllByUserId(Long userId, Integer requestStatus);

	@Query(value="select * from stock_transaction where user_id=:userId and stock_id=:stockId and status=:requestStatus",nativeQuery = true)
	public Optional<StockTransaction> getTransactionByUserIdAndStockId(Long userId, Long stockId, Integer requestStatus);

	@Query("from StockTransaction strnx where strnx.user.id = :userId and strnx.stock.id = :stockId")
	StockTransaction getTrnxByUserIdAndStockId(@Param("userId") Long userId,@Param("stockId") Long stockId);

}
