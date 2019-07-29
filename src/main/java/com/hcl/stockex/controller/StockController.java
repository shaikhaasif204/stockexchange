package com.hcl.stockex.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks")
public class StockController {
	
	@RequestMapping("")
	@GetMapping
	public ResponseEntity<Object> getAllStockDetails(){
		return null;
	}

}
