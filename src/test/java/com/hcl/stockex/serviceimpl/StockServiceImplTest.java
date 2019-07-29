package com.hcl.stockex.serviceimpl;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.entity.Stock;
import com.hcl.stockex.exception.ApplicationException;
import com.hcl.stockex.repository.StockRepository;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceImplTest {
	
	@InjectMocks
	StockServiceImpl stockServiceImpl;

	@Mock
	StockRepository stockRepositoryMock;
	
	ResponseDTO responseDTO;
	List<Stock> stockList = new ArrayList<>();

	@Before
	public void setUp() {
		responseDTO = new ResponseDTO();
		
	}
	
	@Test
	public void testgetAllStockDetailsNoDetails() throws ApplicationException {
		
		Mockito.when(stockRepositoryMock.findAll()).thenReturn(stockList);
		assertNotNull(stockServiceImpl.getAllStockDetails());
		
	}
	
	@Test
	public void testgetAllStockDetailsWithDetails() throws ApplicationException {
		stockList.add(new Stock());
		Mockito.when(stockRepositoryMock.findAll()).thenReturn(stockList);
		assertNotNull(stockServiceImpl.getAllStockDetails());
		
	}
	

}
