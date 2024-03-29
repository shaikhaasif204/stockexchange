package com.hcl.stockex.serviceimpl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.stockex.dto.PurchaseRequestDTO;
import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.entity.Stock;
import com.hcl.stockex.entity.StockTransaction;
import com.hcl.stockex.entity.User;
import com.hcl.stockex.exception.ApplicationException;
import com.hcl.stockex.repository.StockRepository;
import com.hcl.stockex.repository.StockTransactionRepository;
import com.hcl.stockex.repository.UserRepository;
import com.hcl.stockex.util.RequestStatusUtil;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceImplTest {

	@InjectMocks
	PurchaseServiceImpl purchaseServiceImpl;

	@Mock
	StockTransactionRepository stockTransactionRepository;
	
	@Mock
	StockRepository stockRepository;
	
	@Mock
	UserRepository userRepository;

	PurchaseRequestDTO purchaseRequestDTO;

	ResponseDTO responseDTO;

	@Before
	public void setUp() {
		purchaseRequestDTO = new PurchaseRequestDTO();
		responseDTO = new ResponseDTO();
		
	}
	
	@Test
	public void testReviewPurchaseIfPurchaseDetailsAreCorrect() {
		StockTransaction stockTransaction = new StockTransaction();
		purchaseRequestDTO.setUserId(1L);
		purchaseRequestDTO.setStockId(1L);
		
		User user = new User();
		user.setId(2L);
		
		Stock stock = new Stock();
		stock.setId(1L);
		stock.setStockName("SBI Homes");
		stock.setStockType("Trading");
		stock.setConfirmPrice(235.0);
		stockTransaction.setStock(stock);
		stockTransaction.setUser(user);
		stockTransaction.setQuantity(100);
		Optional<StockTransaction> stockTransact = Optional.of(stockTransaction);
		when(stockTransactionRepository.getTransactionByUserIdAndStockId(purchaseRequestDTO.getUserId(),purchaseRequestDTO.getStockId(), RequestStatusUtil.INITIATED)).thenReturn(stockTransact);
		assertNotNull(purchaseServiceImpl.reviewPurchase(purchaseRequestDTO));
	}
	
	@Test
	public void testReviewPurchaseIfPurchaseDetailsAreIncorrect() {
		StockTransaction stockTransaction = new StockTransaction();
		purchaseRequestDTO.setUserId(1L);
		purchaseRequestDTO.setStockId(1L);
		
		User user = new User();
		user.setId(2L);
		
		Stock stock = new Stock();
		stock.setId(1L);
		stock.setStockName("SBI Homes");
		stock.setStockType("Trading");
		stock.setConfirmPrice(235.0);
		stockTransaction.setStock(stock);
		stockTransaction.setUser(user);
		stockTransaction.setQuantity(100);
		Optional<StockTransaction> stockTransact = Optional.empty();
		when(stockTransactionRepository.getTransactionByUserIdAndStockId(purchaseRequestDTO.getUserId(),purchaseRequestDTO.getStockId(), RequestStatusUtil.INITIATED)).thenReturn(stockTransact);
		assertNotNull(purchaseServiceImpl.reviewPurchase(purchaseRequestDTO));
	}
	
	@Test
	public void testPurchaseStockIfPurchaseDetailsAreCorrect() throws ApplicationException {
		StockTransaction stockTransaction = new StockTransaction();
		purchaseRequestDTO.setUserId(1L);
		purchaseRequestDTO.setStockId(1L);
		purchaseRequestDTO.setQuantityOfStock(100);
		User user = new User();
		user.setId(2L);
		Optional<User> userOpt = Optional.of(user);
		when(userRepository.findById(purchaseRequestDTO.getUserId())).thenReturn(userOpt);
		
		Stock stock = new Stock();
		//stock.setId(1L);
		stock.setStockName("SBI Homes");
		stock.setStockType("Trading");
		stock.setConfirmPrice(235.0);
		stock.setPurchasePrice(25.0);
		stockTransaction.setStock(stock);
		stockTransaction.setUser(user);
		stockTransaction.setQuantity(100);
		stockTransaction.setId(2L);
		stockTransaction.setStockPrice(25.0);
		stockTransaction.setTotalPrice(2500.0);
		stockTransaction.setStatus(1);
		Optional<Stock> stockTransact = Optional.of(stock);
		StockTransaction savedStockTransaction = new StockTransaction();
		savedStockTransaction.setId(1L);
		when(stockRepository.findById(purchaseRequestDTO.getStockId())).thenReturn(stockTransact);
		when(stockTransactionRepository.save(Mockito.any(StockTransaction.class))).thenReturn(stockTransaction);
		assertNotNull(purchaseServiceImpl.purchaseStock(purchaseRequestDTO));
	}
	
	@Test
	public void testCOmpleteTrnxIfPurchaseDetailsAreCorrect() throws ApplicationException {
		StockTransaction stockTransaction = new StockTransaction();
		purchaseRequestDTO.setUserId(1L);
		purchaseRequestDTO.setStockId(1L);
		purchaseRequestDTO.setQuantityOfStock(100);
		purchaseRequestDTO.setStatus(RequestStatusUtil.EXECUTED);
		User user = new User();
		user.setId(2L);
		
		Stock stock = new Stock();
		stock.setStockName("SBI Homes");
		stock.setStockType("Trading");
		stock.setConfirmPrice(235.0);
		stock.setExecutePrice(26.0);
		stock.setPurchasePrice(25.0);
		stockTransaction.setStock(stock);
		stockTransaction.setUser(user);
		stockTransaction.setQuantity(100);
		stockTransaction.setStatus(RequestStatusUtil.REVIEWED);
		Optional<Stock> stockTransact = Optional.of(stock);
		//StockTransaction savedStockTransaction = new StockTransaction();
		
		when(stockRepository.findById(purchaseRequestDTO.getStockId())).thenReturn(stockTransact);
		when(stockTransactionRepository.getTrnxByUserIdAndStockId(purchaseRequestDTO.getUserId(), purchaseRequestDTO.getStockId())).thenReturn(stockTransaction);
		assertNotNull(purchaseServiceImpl.completeTrnx(purchaseRequestDTO));
	}
}
