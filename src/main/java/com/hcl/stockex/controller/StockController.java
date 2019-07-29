package com.hcl.stockex.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.stockex.exception.ApplicationException;
import com.hcl.stockex.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {
	
	private static final Logger logger = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
	StockService stockService;
	
	@GetMapping("")
	public ResponseEntity<Object> getAllStockDetails() throws ApplicationException{
		logger.info("Received get All Stock Details request.");
		return new ResponseEntity<>(stockService.getAllStockDetails(), HttpStatus.OK);
	}

}
