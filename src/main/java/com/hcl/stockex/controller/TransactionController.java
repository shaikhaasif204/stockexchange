package com.hcl.stockex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.exception.ApplicationException;
import com.hcl.stockex.serviceimpl.TransactionServiceImpl;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {
	
	@Autowired
	TransactionServiceImpl transactionServiceImpl;
	
	
	
	@GetMapping(value = "/history")
	public ResponseEntity<ResponseDTO> getHistory(@RequestParam Long userId) throws ApplicationException {
		if(null == userId)
		throw new ApplicationException("user Id cannot be empty");
		ResponseDTO returnedResponse = transactionServiceImpl.getHistory(userId);
		return new ResponseEntity<ResponseDTO>(returnedResponse, HttpStatus.OK);
		
	}
	

}
