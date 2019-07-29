package com.hcl.stockex.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.repository.StockTransactionRepository;
import com.hcl.stockex.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	StockTransactionRepository stockTransactionRepository;
	
	
	@Override
	public ResponseDTO getHistory(Long userId) {
		
		return null;
	}

	

}
