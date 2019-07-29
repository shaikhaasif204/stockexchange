package com.hcl.stockex.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.exception.ApplicationException;
import com.hcl.stockex.service.StockService;

@RunWith(MockitoJUnitRunner.class)
public class StockControllerTest {
	
	@InjectMocks
	StockController stockController;
	
	@Mock
	StockService stockServiceMock;
	
	ResponseDTO responseDTO = new ResponseDTO();
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testgetAllStockDetails() throws ApplicationException {
		Mockito.when(stockServiceMock.getAllStockDetails()).thenReturn(responseDTO);
		assertNotNull(stockController.getAllStockDetails());
		
	}

}
