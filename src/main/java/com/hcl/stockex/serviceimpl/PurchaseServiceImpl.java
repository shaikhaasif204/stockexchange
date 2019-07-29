package com.hcl.stockex.serviceimpl;

import org.springframework.stereotype.Service;


import com.hcl.stockex.dto.PurchaseRequestDTO;
import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.exception.ApplicationException;

import com.hcl.stockex.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService{


	@Override
	public ResponseDTO purchaseStock(PurchaseRequestDTO purchaseRequestDTO) throws ApplicationException {
		
		return null;
	}


}
