package com.hcl.stockex.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.stockex.dto.PurchaseRequestDTO;
import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.exception.ApplicationException;
import com.hcl.stockex.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;
	
	
	private static final String ERROR_MSG = "Mandetory Element missing : ";
	
	
	@PostMapping("/initiate")
	public ResponseEntity<Object> purchaseOrder(@RequestBody PurchaseRequestDTO purchaseRequestDTO) throws ApplicationException {
		validateRequest(purchaseRequestDTO);
		return new ResponseEntity<>(purchaseService.purchaseStock(purchaseRequestDTO), HttpStatus.OK);
	}
	
	@PutMapping("/executed")
	public ResponseEntity<Object> executedTrnx(@RequestBody PurchaseRequestDTO purchaseRequestDTO ) throws ApplicationException{
		return new ResponseEntity<>(purchaseService.completeTrnx(purchaseRequestDTO), HttpStatus.OK);
	}
	@PutMapping("/review")
	public ResponseEntity<Object> reviewPurchase(@RequestBody PurchaseRequestDTO purchaseRequestDTO) throws ApplicationException{
		validateRequest(purchaseRequestDTO);
		ResponseDTO responseDTO = purchaseService.reviewPurchase(purchaseRequestDTO);
		if(null != responseDTO.getData()) {
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		}
		throw new ApplicationException("Please enter valid details");
	}
	
	
	private void validateRequest(PurchaseRequestDTO purchaseRequestDTO) throws ApplicationException{
		
		  if(StringUtils.isEmpty(purchaseRequestDTO.getStockName())) { 
			  throw new ApplicationException(ERROR_MSG + "StockName"); 
			  } 
		  if(null == purchaseRequestDTO.getStockId()) {
			  throw new ApplicationException(ERROR_MSG + "Stock Id"); 
			  } 
		  if(null == purchaseRequestDTO.getUserId()) { 
			  throw new ApplicationException(ERROR_MSG + "UserId");
			  } 
		  
		  if(null == purchaseRequestDTO.getQuantityOfStock() || 0 >= purchaseRequestDTO.getQuantityOfStock()) { 
			  throw new ApplicationException(ERROR_MSG + "Quantity of Stock"); 
			  }
		  
		  
		 	
	}
	
}
