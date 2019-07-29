package com.hcl.stockex.serviceimpl;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.stockex.entity.Stock;
import com.hcl.stockex.entity.StockTransaction;
import com.hcl.stockex.entity.User;
import com.hcl.stockex.repository.StockTransactionRepository;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

	@InjectMocks
	TransactionServiceImpl transactionServiceImpl;
	
	@Mock
	StockTransactionRepository stockTransactionRepository;
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void testGetHistory() {
		StockTransaction stockTransaction = new StockTransaction();
		
		User user = new User();
		user.setId(2L);
		
		Stock stock = new Stock();
		stock.setId(1L);
		stock.setStockName("SBI Homes");
		stock.setStockType("Trading");
		stockTransaction.setStock(stock);
		stockTransaction.setUser(user);
		stockTransaction.setQuantity(100);
		stockTransaction.setTotalPrice(2500.0);
		stockTransaction.setStockPrice(25.0);
		List<StockTransaction> history = new ArrayList<>();
		history.add(stockTransaction);
		when(stockTransactionRepository.findAllByUserId(1L)).thenReturn(history);
		assertNotNull(transactionServiceImpl.getHistory(1L));
	}
}
