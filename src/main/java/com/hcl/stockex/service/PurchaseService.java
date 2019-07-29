package com.hcl.stockex.service;

import org.springframework.stereotype.Service;

import com.hcl.stockex.dto.PurchaseRequestDTO;
import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.exception.ApplicationException;

@Service
public interface PurchaseService {
	
	public ResponseDTO purchaseStock(PurchaseRequestDTO purchaseRequestDTO) throws ApplicationException;
}
