package com.hcl.stockex.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.stockex.serviceimpl.PurchaseServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseControllerTest {

	@InjectMocks
	PurchaseController purchaseController;
	
	@Mock
	PurchaseServiceImpl purchaseServiceImpl;
	
	@Before
	public void setUp() {
		
	}
}
