package com.hcl.stockex.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.stockex.dto.PurchaseRequestDTO;
import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.exception.ApplicationException;
import com.hcl.stockex.serviceimpl.PurchaseServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseControllerTest {

	@InjectMocks
	PurchaseController purchaseController;
	
	@Mock
	PurchaseServiceImpl purchaseServiceImpl;
	
	PurchaseRequestDTO purchaseRequestDTO;
	
	ResponseDTO responseDTO;
	
	@Before
	public void setUp() {
		purchaseRequestDTO = new PurchaseRequestDTO();
		responseDTO = new ResponseDTO();
	}
	
	@Test
	public void testPurchaseOrderIfPurchaseDetailsAreCorrect() throws ApplicationException {
		purchaseRequestDTO.setUserId(1L);
		purchaseRequestDTO.setStockId(1L);
		purchaseRequestDTO.setStockName("SBI Homes");
		purchaseRequestDTO.setQuantityOfStock(100);
		assertNotNull(purchaseController.purchaseOrder(purchaseRequestDTO));
	}
	
	@Test
	public void testReviewPurchaseIfPurchaseDetailsAreCorrect() throws ApplicationException {
		purchaseRequestDTO.setUserId(1L);
		purchaseRequestDTO.setStockId(1L);
		purchaseRequestDTO.setStockName("SBI Homes");
		purchaseRequestDTO.setQuantityOfStock(100);
		
		responseDTO.setData(Object.class);
		when(purchaseServiceImpl.reviewPurchase(purchaseRequestDTO)).thenReturn(responseDTO);
		assertNotNull(purchaseController.reviewPurchase(purchaseRequestDTO));
	}
	
	@Test(expected = ApplicationException.class)
	public void testReviewPurchaseIfPurchaseDetailsAreIncorrect() throws ApplicationException {
		purchaseRequestDTO.setUserId(1L);
		purchaseRequestDTO.setStockId(1L);
		purchaseRequestDTO.setStockName("SBI Homes");
		purchaseRequestDTO.setQuantityOfStock(100);
		
		when(purchaseServiceImpl.reviewPurchase(purchaseRequestDTO)).thenReturn(responseDTO);
		assertNull(purchaseController.reviewPurchase(purchaseRequestDTO));
	}
	
	@Test(expected = ApplicationException.class)
	public void testReviewPurchaseIfUserIdIsNull() throws ApplicationException {
		purchaseRequestDTO.setUserId(null);
		purchaseRequestDTO.setStockId(1L);
		purchaseRequestDTO.setStockName("SBI Homes");
		purchaseRequestDTO.setQuantityOfStock(100);
		
		assertNull(purchaseController.reviewPurchase(purchaseRequestDTO));
	}
	
	@Test(expected = ApplicationException.class)
	public void testReviewPurchaseIfStockIdIsNull() throws ApplicationException {
		purchaseRequestDTO.setUserId(1L);
		purchaseRequestDTO.setStockId(null);
		purchaseRequestDTO.setStockName("SBI Homes");
		purchaseRequestDTO.setQuantityOfStock(100);
		
		assertNull(purchaseController.reviewPurchase(purchaseRequestDTO));
	}
	
	@Test(expected = ApplicationException.class)
	public void testPurchaseOrderIfStockNameIsEmpty() throws ApplicationException {
		purchaseRequestDTO.setUserId(1L);
		purchaseRequestDTO.setStockId(1L);
		purchaseRequestDTO.setStockName("");
		purchaseRequestDTO.setQuantityOfStock(100);
		
		assertNull(purchaseController.reviewPurchase(purchaseRequestDTO));
	}
	
	@Test(expected = ApplicationException.class)
	public void testPurchaseOrderIfStockQuantityIsNull() throws ApplicationException {
		purchaseRequestDTO.setUserId(1L);
		purchaseRequestDTO.setStockId(1L);
		purchaseRequestDTO.setStockName("SBI Homes");
		purchaseRequestDTO.setQuantityOfStock(null);
		
		assertNull(purchaseController.reviewPurchase(purchaseRequestDTO));
	}
	
	@Test(expected = ApplicationException.class)
	public void testPurchaseOrderIfStockQuantityIsZero() throws ApplicationException {
		purchaseRequestDTO.setUserId(1L);
		purchaseRequestDTO.setStockId(1L);
		purchaseRequestDTO.setStockName("SBI Homes");
		purchaseRequestDTO.setQuantityOfStock(0);
		
		assertNull(purchaseController.reviewPurchase(purchaseRequestDTO));
	}
}
