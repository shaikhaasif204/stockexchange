package com.hcl.stockex.service;

import com.hcl.stockex.dto.ResponseDTO;

public interface TransactionService {

	public ResponseDTO getHistory(Long userId);
	
}
