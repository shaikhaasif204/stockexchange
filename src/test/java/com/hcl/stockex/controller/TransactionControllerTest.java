package com.hcl.stockex.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.serviceimpl.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

	@InjectMocks
	TransactionController transactionController;
	
	@Mock
	TransactionServiceImpl transactionServiceImpl;
	
	ResponseDTO responseDTO;
	
	@Before
	public void setUp() {
		responseDTO = new ResponseDTO();
	}
	
	@Test
	public void testGetHistoryIfUserIdIsCorrect() {
		assertNotNull(transactionController.getHistory(1L));
		
	}
}
