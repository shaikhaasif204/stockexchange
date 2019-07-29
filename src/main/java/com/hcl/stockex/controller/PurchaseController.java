package com.hcl.stockex.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.stockex.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;
	
	@PutMapping("/review")
	public ResponseEntity<Object> reviewPurchase(){
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}
	
}
